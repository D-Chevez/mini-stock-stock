package org.kodigo.mini_stock_sytem.service.Supplier;


import lombok.RequiredArgsConstructor;
import org.kodigo.mini_stock_sytem.model.Supplier;
import org.kodigo.mini_stock_sytem.repository.SupplierRepository;
import org.kodigo.mini_stock_sytem.web.dto.SupplierDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;

    private SupplierDTO mapToDTO(Supplier supplier) {
        return SupplierDTO.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .active(supplier.isActive())
                .build();
    }

    private Supplier mapToEntity(SupplierDTO dto) {
        return Supplier.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .active(dto.isActive())
                .build();
    }

    @Override
    public SupplierDTO create(SupplierDTO dto) {
        Supplier supplier = mapToEntity(dto);
        return mapToDTO(repository.save(supplier));
    }

    @Override
    public SupplierDTO update(Long id, SupplierDTO dto) {
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setActive(dto.isActive());

        return mapToDTO(repository.save(supplier));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Proveedor no encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public SupplierDTO getById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Proveedor nno encontrado"));
    }

    @Override
    public List<SupplierDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



}
