package org.kodigo.mini_stock_sytem.service.Product;

import java.util.List;
import org.kodigo.mini_stock_sytem.web.dto.product.ProductRequest;
import org.kodigo.mini_stock_sytem.web.dto.product.ProductResponse;

public interface ProductService {
    ProductResponse create(ProductRequest dto);
    ProductResponse update(Long id, ProductRequest dto);
    void delete(Long id);
    ProductResponse getById(Long id);
    List<ProductResponse> getAll();
}
