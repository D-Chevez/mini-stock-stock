package org.kodigo.mini_stock_sytem.service.Supplier;

import org.kodigo.mini_stock_sytem.web.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {

    SupplierDTO create(SupplierDTO dto);
    SupplierDTO update(Long id,  SupplierDTO dto);
    void delete(Long id);
    SupplierDTO getById(Long id);
    List<SupplierDTO> getAll();

}
