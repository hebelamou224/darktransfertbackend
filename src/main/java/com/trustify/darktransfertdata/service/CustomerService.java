package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.Operation;
import com.trustify.darktransfertdata.model.Customer;
import com.trustify.darktransfertdata.repository.CustomerRepository;
import com.trustify.darktransfertdata.service.proxy.CustomerServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        if (customerOptional.isPresent()) {
            System.out.println(customerOptional.get());
        } else {
            System.out.println("Not data is present, identify = " + identify);
        }
        return customerOptional.orElse(null);
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

    public List<Customer> findAllByOrderByOperationDateModifyDesc() {
        return this.customerRepository.findAllByOrderByOperationDateModifyDesc();
    }

    public List<Customer> findAllByFullnameContainingOrFullnameReceverContainingOrIdentifyContainingOrderByOperationDateModifyDesc(String fullname, String fullnameRecever, String identify) {
        return this.customerRepository.findAllByFullnameContainingOrFullnameReceverContainingOrIdentifyContainingOrderByOperationDateModifyDesc(fullname, fullnameRecever, identify);
    }

    public Customer findFirstByOrderByOperationDateModifyDesc() {
        Optional<Customer> optionalCustomer = this.customerRepository.findFirstByOrderByOperationDateModifyDesc();
        return optionalCustomer.orElse(null);
    }

}
