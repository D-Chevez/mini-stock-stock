package org.kodigo.mini_stock_sytem.service.Customer;


import lombok.RequiredArgsConstructor;
import org.kodigo.mini_stock_sytem.model.Customer;
import org.kodigo.mini_stock_sytem.repository.CustomerRepository;
import org.kodigo.mini_stock_sytem.web.dto.customer.CustomerRequest;
import org.kodigo.mini_stock_sytem.web.dto.customer.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    private CustomerResponse mapToResponse(Customer customer){
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        response.setPhone(customer.getPhone());
        // Si hay más campos, agregarlos aquí
        return response;
    }

    private Customer mapToEntity(CustomerRequest dto) {
        return Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                // Si hay más campos, agregarlos aquí
                .build();
    }

    @Override
    public CustomerResponse create(CustomerRequest dto) {
        Customer customer = mapToEntity(dto);
        return mapToResponse(repository.save(customer));
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest dto) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        // Si hay más campos, agregarlos aquí
        return mapToResponse(repository.save(customer));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public CustomerResponse getById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return mapToResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

}
