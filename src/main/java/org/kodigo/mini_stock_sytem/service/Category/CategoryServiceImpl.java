package org.kodigo.mini_stock_sytem.service.Category;

import lombok.RequiredArgsConstructor;
import org.kodigo.mini_stock_sytem.model.Category;
import org.kodigo.mini_stock_sytem.repository.CategoryRepository;
import org.kodigo.mini_stock_sytem.web.dto.category.CategoryRequest;
import org.kodigo.mini_stock_sytem.web.dto.category.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    private CategoryResponse mapToResponse(Category category){
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        // Si hay más campos, agregarlos aquí
        return response;
    }

    private Category mapToEntity(CategoryRequest dto) {
        return Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                // Si hay más campos, agregarlos aquí
                .build();
    }

    @Override
    public CategoryResponse create(CategoryRequest dto) {
        Category category = mapToEntity(dto);
        return mapToResponse(repository.save(category));
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest dto) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        // Si hay más campos, agregarlos aquí
        return mapToResponse(repository.save(category));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return mapToResponse(category);
    }

    @Override
    public List<CategoryResponse> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
