package org.kodigo.mini_stock_sytem.service.Customer;


import lombok.RequiredArgsConstructor;
import org.kodigo.mini_stock_sytem.model.Customer;
import org.kodigo.mini_stock_sytem.repository.CustomerRepository;
import org.kodigo.mini_stock_sytem.web.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    private CustomerDTO mapToDTO(Customer customer){
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .active(customer.isActive())
                .build();
    }

    private Customer mapToEntity(CustomerDTO dto) {
        return Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .active(dto.isActive())
                .build();
    }

    @Override
    public CustomerDTO create(CustomerDTO dto) {
        Customer customer = mapToEntity(dto);
        return mapToDTO(repository.save(customer));
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setActive(dto.isActive());
        return mapToDTO(repository.save(customer));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public CustomerDTO getById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));
    }

    @Override
    public List<CustomerDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

}

