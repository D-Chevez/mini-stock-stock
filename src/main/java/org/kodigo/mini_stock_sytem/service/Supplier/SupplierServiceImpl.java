package org.kodigo.mini_stock_sytem.service.Supplier;


import lombok.RequiredArgsConstructor;
import org.kodigo.mini_stock_sytem.model.Supplier;
import org.kodigo.mini_stock_sytem.repository.SupplierRepository;
import org.kodigo.mini_stock_sytem.web.dto.supplier.SupplierRequest;
import org.kodigo.mini_stock_sytem.web.dto.supplier.SupplierResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository repository;

    private SupplierResponse mapToResponse(Supplier supplier) {
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setEmail(supplier.getEmail());
        response.setPhone(supplier.getPhone());
        return response;
    }

    private Supplier mapToEntity(SupplierRequest dto) {
        return Supplier.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public SupplierResponse create(SupplierRequest dto) {
        Supplier supplier = mapToEntity(dto);
        return mapToResponse(repository.save(supplier));
    }

    @Override
    public SupplierResponse update(Long id, SupplierRequest dto) {
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        supplier.setName(dto.getName());
        supplier.setPhone(dto.getPhone());
        supplier.setEmail(dto.getEmail());
        return mapToResponse(repository.save(supplier));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Proveedor no encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public SupplierResponse getById(Long id) {
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        return mapToResponse(supplier);
    }

    @Override
    public List<SupplierResponse> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }



}
