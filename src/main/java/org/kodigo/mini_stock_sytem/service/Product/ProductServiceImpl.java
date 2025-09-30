package org.kodigo.mini_stock_sytem.service.Product;

import org.kodigo.mini_stock_sytem.model.Product;
import org.kodigo.mini_stock_sytem.repository.ProductRepository;
import org.kodigo.mini_stock_sytem.service.Product.ProductService;
import org.kodigo.mini_stock_sytem.web.dto.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    private ProductDTO mapToDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .category(product.getCategory())
                .priceCents(product.getPriceCents())
                .minStocks(product.getMinStocks())
                .stock(product.getStock)
                .active(product.isActive())
                .build();
    }

    private Product mapToEntity(ProductDTO dto) {
        return Product.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .category(product.getCategory())
                .priceCents(product.getPriceCents())
                .minStocks(product.getMinStocks())
                .stock(product.getStock)
                .active(product.isActive())
                .build();
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        Product product = mapToEntity(dto);
        return mapToDTO(repository.save(product));
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        product.setSku(dto.getSku());
        product.setName(dto.getName());
        product.setPriceCents(dto.getPriceCents());
        product.setMinStocks(dto.getMinStocks());
        product.setStock(dto.getStock());
        product.setActive(dto.isActive());
        return mapToDTO(repository.save(product));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public ProductDTO getById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));
    }

    @Override
    public List<ProductDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}


}
