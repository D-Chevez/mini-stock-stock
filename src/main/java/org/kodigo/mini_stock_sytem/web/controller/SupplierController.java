package org.kodigo.mini_stock_sytem.web.controller;


import lombok.RequiredArgsConstructor;
import org.kodigo.mini_stock_sytem.service.Supplier.SupplierService;
import org.kodigo.mini_stock_sytem.web.dto.supplier.SupplierRequest;
import org.kodigo.mini_stock_sytem.web.dto.supplier.SupplierResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    @Autowired
    private SupplierService service;

    @PostMapping
    public ResponseEntity<SupplierResponse> create(@RequestBody SupplierRequest dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> update(@PathVariable Long id, @RequestBody SupplierRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}
