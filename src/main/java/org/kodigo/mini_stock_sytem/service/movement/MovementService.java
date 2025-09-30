package org.kodigo.mini_stock_sytem.service.movement;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.*;

import org.kodigo.mini_stock_sytem.model.Customer;
import org.kodigo.mini_stock_sytem.model.Supplier;
import org.kodigo.mini_stock_sytem.model.Product;
import org.kodigo.mini_stock_sytem.model.Movement;
import org.kodigo.mini_stock_sytem.model.MovementType;

import org.kodigo.mini_stock_sytem.repository.movement.MovementRepository;
//import org.kodigo.mini_stock_sytem.repository.product.ProductRepository; //TODO
//import org.kodigo.mini_stock_sytem.repository.customer.CustomerRepository; //TODO
//import org.kodigo.mini_stock_sytem.repository.supplier.SupplierRepository; //TODO

import org.kodigo.mini_stock_sytem.repository.movement.MovementSpecifications;
import org.kodigo.mini_stock_sytem.web.dto.movement.MovementRequest;
import org.kodigo.mini_stock_sytem.web.dto.movement.MovementResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class MovementService {

    private final MovementRepository movementRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;

    @Transactional
    public MovementResponse create(@Valid MovementRequest req, String actor) {
        if (req.idempotencyKey() != null && movementRepository.existsByIdempotencyKey(req.idempotencyKey())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicated idempotency key");
        }

        Product product = productRepository.findById(req.productId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Customer customer = (req.customerId() == null) ? null :
                customerRepository.findById(req.customerId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found"));

        Supplier supplier = (req.supplierId() == null) ? null :
                supplierRepository.findById(req.supplierId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supplier not found"));

        // Domain validations:
        if (req.type() == MovementType.OUT && (product.getStock() == null || product.getStock() < req.quantity())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock");
        }

        // Build entity:
        Movement mov = new Movement();
        mov.setProduct(product);
        mov.setCustomer(customer);
        mov.setSupplier(supplier);
        mov.setType(req.type());
        mov.setQty(req.quantity());
        mov.setIdempotencyKey(req.idempotencyKey());
        mov.setCreatedBy(actor);
        mov.setCreatedAt(Instant.now());

        // Persist movement first (so we get an ID if needed for auditing)
        mov = movementRepository.save(mov);

        // Adjust stock according to the movement:
        applyStockDelta(product, req.type(), req.quantity(), /*isReversal*/ false);

        productRepository.save(product);

        return toResponse(mov);
    }

    @Transactional
    public MovementResponse update(Long id, @Valid MovementRequest req, String actor) {
        Movement current = movementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movement not found"));

        // Revert old stock effect first
        Product oldProduct = current.getProduct();
        applyStockDelta(oldProduct, current.getType(), current.getQty(), /*isReversal*/ true);
        productRepository.save(oldProduct);

        // Load new references
        Product newProduct = productRepository.findById(req.productId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Customer newCustomer = (req.customerId() == null) ? null :
                customerRepository.findById(req.customerId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found"));

        Supplier newSupplier = (req.supplierId() == null) ? null :
                supplierRepository.findById(req.supplierId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supplier not found"));

        // Validate new effect (e.g., OUT cannot leave negative)
        if (req.type() == MovementType.OUT
                && (newProduct.getStock() == null || newProduct.getStock() < req.quantity())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock for new values");
        }

        // Apply new values
        current.setProduct(newProduct);
        current.setCustomer(newCustomer);
        current.setSupplier(newSupplier);
        current.setType(req.type());
        current.setQty(req.quantity());
        current.setIdempotencyKey(req.idempotencyKey()); // can be null; unique if not null
        current.setCreatedBy(current.getCreatedBy() == null ? actor : current.getCreatedBy());
        // keep createdAt

        // Apply new stock effect
        applyStockDelta(newProduct, req.type(), req.quantity(), /*isReversal*/ false);
        productRepository.save(newProduct);

        return toResponse(current);
    }

    @Transactional
    public void delete(Long id) {
        Movement current = movementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movement not found"));

        // Revert stock impact before deleting
        Product product = current.getProduct();
        applyStockDelta(product, current.getType(), current.getQty(), /*isReversal*/ true);

        // Validate that reversal does not cause negative stock (shouldn't for IN; for OUT reversal increases stock)
        if (product.getStock() != null && product.getStock() < 0) {
            // undo the reversal to keep consistency and abort
            applyStockDelta(product, current.getType(), current.getQty(), /*isReversal*/ false);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot delete movement due to stock constraints");
        }

        productRepository.save(product);
        movementRepository.delete(current);
    }

    public MovementResponse findById(Long id) {
        return movementRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movement not found"));
    }

    public List<MovementResponse> search(
            Long productId,
            Long customerId,
            Long supplierId,
            MovementType type,
            LocalDate dateFrom,
            LocalDate dateTo,
            String sortBy,
            String sortDir
    ) {
        // Whitelist of allowed sort properties (avoid invalid property exceptions)
        Set<String> allowed = Set.of("createdAt", "id", "quantity", "unitPriceCents");
        if (sortBy == null || sortBy.isBlank() || !allowed.contains(sortBy)) {
            sortBy = "createdAt";
        }
        if (sortDir == null || (!sortDir.equalsIgnoreCase("asc") && !sortDir.equalsIgnoreCase("desc"))) {
            sortDir = "desc";
        }

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        List<Movement> movements = movementRepository.findAll(
                MovementSpecifications.withFilters(productId, customerId, supplierId, type, dateFrom, dateTo),
                sort
        );

        return movements.stream()
                .map(MovementResponse::fromEntity)
                .toList();
    }

    // --- helpers ---

    private void applyStockDelta(Product product, MovementType type, int qty, boolean isReversal) {
        int delta = switch (type) {
            case IN -> +qty;
            case OUT -> -qty;
            case ADJUST -> +qty; // keep simple; if in your domain ADJUST may be negative, adapt here
        };
        if (isReversal) delta = -delta;

        Integer curr = product.getStock() == null ? 0 : product.getStock();
        int next = curr + delta;

        if (next < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock would become negative");
        }
        product.setStock(next);
    }

    private MovementResponse toResponse(Movement m) {
        return new MovementResponse(
                m.getId(),
                m.getProduct() != null ? m.getProduct().getId() : null,
                (m.getProduct() != null) ? m.getProduct().getSku() : null,
                (m.getProduct() != null) ? m.getProduct().getName() : null,
                (m.getCustomer() != null) ? m.getCustomer().getId() : null,
                (m.getCustomer() != null) ? m.getCustomer().getName() : null,
                (m.getSupplier() != null) ? m.getSupplier().getId() : null,
                (m.getSupplier() != null) ? m.getSupplier().getName() : null,
                m.getType(),
                m.getQty(),
                (m.getProduct() != null) ? BigDecimal.valueOf(m.getProduct().getPriceCents(), 2) : null,
                m.getIdempotencyKey(),
                m.getCreatedAt(),
                m.getCreatedBy()
        );
    }
}

