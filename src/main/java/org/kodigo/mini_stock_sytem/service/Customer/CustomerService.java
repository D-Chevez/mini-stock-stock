package org.kodigo.mini_stock_sytem.service.Customer;

import org.kodigo.mini_stock_sytem.web.dto.customer.CustomerRequest;
import org.kodigo.mini_stock_sytem.web.dto.customer.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse create(CustomerRequest dto);
    CustomerResponse update(Long id,CustomerRequest dto);
    void delete(Long id);
    CustomerResponse getById(Long id);
    List<CustomerResponse> getAll();
}