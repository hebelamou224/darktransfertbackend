package com.trustify.darktransfertdata.service.proxy;

import com.trustify.darktransfertdata.model.Customer;

public interface CustomerServiceInterface {
    public Customer deposit(Customer customer);
    public Iterable<Customer> findAllTransactionWithCustomer();

    public Customer findById(Long id);
}
