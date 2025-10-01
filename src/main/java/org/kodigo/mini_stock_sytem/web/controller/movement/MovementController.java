package org.kodigo.mini_stock_sytem.web.controller.movement;

import jakarta.validation.Valid;
import lombok.*;
import org.kodigo.mini_stock_sytem.model.MovementType;
import org.kodigo.mini_stock_sytem.service.movement.MovementService;
import org.kodigo.mini_stock_sytem.web.dto.movement.MovementRequest;
import org.kodigo.mini_stock_sytem.web.dto.movement.MovementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movements")
@RequiredArgsConstructor
@Validated
public class MovementController {

    @Autowired
    private MovementService movementService;

    @PostMapping
    public ResponseEntity<MovementResponse> create(@Valid @RequestBody MovementRequest request) {
        MovementResponse created = movementService.create(request, "system");

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovementResponse> update(@PathVariable Long id,
                                                   @Valid @RequestBody MovementRequest request) {
        MovementResponse updated = movementService.update(id, request, "system");
        return ResponseEntity.ok(updated);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovementResponse> getById(@PathVariable Long id) {
        MovementResponse response = movementService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MovementResponse>> search(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long supplierId,
            @RequestParam(required = false) MovementType type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String sortDir
    ) {
        List<MovementResponse> list = movementService.search(
                productId, customerId, supplierId, type, dateFrom, dateTo, sortBy, sortDir
        );
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
