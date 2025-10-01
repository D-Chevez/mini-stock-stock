package org.kodigo.mini_stock_sytem.service.Supplier;

import java.util.List;
import org.kodigo.mini_stock_sytem.web.dto.supplier.SupplierRequest;
import org.kodigo.mini_stock_sytem.web.dto.supplier.SupplierResponse;

public interface SupplierService {

    SupplierResponse create(SupplierRequest dto);
    SupplierResponse update(Long id, SupplierRequest dto);
    void delete(Long id);
    SupplierResponse getById(Long id);
    List<SupplierResponse> getAll();

}
