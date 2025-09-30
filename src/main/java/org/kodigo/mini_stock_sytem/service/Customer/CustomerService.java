package org.kodigo.mini_stock_sytem.service.Customer;

import org.kodigo.mini_stock_sytem.web.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO create(CustomerDTO dto);
    CustomerDTO update(Long id,CustomerDTO dto);
    void delete(Long id);
    CustomerDTO getById(Long id);
    List<CustomerDTO> getAll();
}