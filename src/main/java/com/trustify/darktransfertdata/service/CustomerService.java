package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.Operation;
import com.trustify.darktransfertdata.model.Customer;
import com.trustify.darktransfertdata.repository.CustomerRepository;
import com.trustify.darktransfertdata.service.proxy.CustomerServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements CustomerServiceInterface {

    private CustomerRepository customerRepository;


    @Override
    public Customer deposit(Customer customer) {
        return null;
    }

    @Override
    public Iterable<Customer> findAllTransactionWithCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    public Customer findByIdentify(String identify){
        Optional<Customer> customerOptional = this.customerRepository.findByIdentify(identify);
        if(customerOptional.isPresent())
            return customerOptional.get();

        throw new RuntimeException("Identifiant introuvale");
    }

    public List<Customer> findByOperationType(Operation type){
        return this.customerRepository.findAllByOperation_Type(type);
    }

    public List<Customer> findByOperationStatus(boolean status){
        return this.customerRepository.findAllByOperation_Status(status);
    }

    public List<Customer> findAllByOperationTypeAndOperationStatus(Operation type, boolean status){
        return this.customerRepository.findAllByOperationTypeAndOperationStatus(type, status);
    }

}
