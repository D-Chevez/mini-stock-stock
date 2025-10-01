package org.kodigo.mini_stock_sytem.service.Product;

import lombok.RequiredArgsConstructor;
import org.kodigo.mini_stock_sytem.model.Product;
import org.kodigo.mini_stock_sytem.repository.ProductRepository;
import org.kodigo.mini_stock_sytem.web.dto.product.ProductRequest;
import org.kodigo.mini_stock_sytem.web.dto.product.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    private ProductResponse mapToResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        return response;
    }

    private Product mapToEntity(ProductRequest dto) {
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
    }

    @Override
    public ProductResponse create(ProductRequest dto) {
        Product product = mapToEntity(dto);
        return mapToResponse(repository.save(product));
    }

    @Override
    public ProductResponse update(Long id, ProductRequest dto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        // Si hay más campos, agregarlos aquí
        return mapToResponse(repository.save(product));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
