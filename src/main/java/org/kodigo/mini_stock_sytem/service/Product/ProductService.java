package org.kodigo.mini_stock_sytem.service.Product;

import org.kodigo.mini_stock_sytem.web.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO create(ProductDTO dto);
    ProductDTO update(Long id, ProductDTO dto);
    void delete(Long id);
    ProductDTO getById(Long id);
    List<ProductDTO> getAll();
}
