package org.kodigo.mini_stock_sytem.service.Category;

import org.kodigo.mini_stock_sytem.web.dto.category.CategoryRequest;
import org.kodigo.mini_stock_sytem.web.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest dto);
    CategoryResponse update(Long id, CategoryRequest dto);
    void delete(Long id);
    CategoryResponse getById(Long id);
    List<CategoryResponse> getAll();
}
