package com.emarket.customer.service.customer;

import com.emarket.customer.service.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;


    public Boolean existsById(String customerId) {
        return repository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("NO CUSTOMER FOUND WITH THE PROVIDED ID:: %S",customerId)
                ));
    }

    public List<CustomerResponse> findAllCustomers() {
        var customers = repository.findAll();
        return customers
                .stream()
                .map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = repository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }


    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }

    public String updateCustomer(@Valid CustomerRequest request) throws CustomerNotFoundException {
        var customerToUpdate = repository.findById(request.id())
                .orElseThrow( () -> new CustomerNotFoundException(
                    String.format("CANNOT UPDATE CUSTOMER:: NO CUSTOMER FOUND WITH THE PROVIDED ID:: %S",request.id())
                ));

        updateExistingCustomer(customerToUpdate, request);
        
        return repository.save(customerToUpdate).getId();
    }

    private void updateExistingCustomer(Customer customerToUpdate, @Valid CustomerRequest request) {
        if(StringUtils.isBlank(request.firstName())){
            customerToUpdate.setFirstName(request.firstName());
        }
        if(StringUtils.isBlank(request.lastName())){
            customerToUpdate.setFirstName(request.lastName());
        }
        if(StringUtils.isBlank(request.email())){
            customerToUpdate.setFirstName(request.email());
        }
        if(request.address() != null){
            customerToUpdate.setAddress(request.address());
        }
    }
}
