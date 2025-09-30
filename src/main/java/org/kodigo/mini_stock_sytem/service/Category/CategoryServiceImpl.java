package org.kodigo.mini_stock_sytem.service.Category;

import org.kodigo.mini_stock_sytem.model.Category;
import org.kodigo.mini_stock_sytem.repository.CategoryRepository;
import org.kodigo.mini_stock_sytem.service.Category.CategoryService;
import org.kodigo.mini_stock_sytem.web.dto.CategoryDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    private CategoryDTO mapToDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .active(category.isActive())
                .build();
    }

    private Category mapToEntity(CategoryDTO dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.isActive())
                .build();
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category category = mapToEntity(dto);
        return mapToDTO(repository.save(category));
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setActive(dto.isActive());
        return mapToDTO(repository.save(category));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public CategoryDTO getById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));
    }

    @Override
    public List<CategoryDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
