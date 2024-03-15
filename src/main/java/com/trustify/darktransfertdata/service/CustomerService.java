package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Customer;
import com.trustify.darktransfertdata.repository.CustomerRepository;
import com.trustify.darktransfertdata.service.proxy.CustomerServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements CustomerServiceInterface {

    private CustomerRepository depositRepository;


    @Override
    public Customer deposit(Customer customer) {
        return null;
    }

    @Override
    public Iterable<Customer> findAllTransactionWithCustomer() {
        return depositRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = depositRepository.findById(id);
        return customer.orElse(null);
    }
}
