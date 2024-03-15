package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.Operation;
import com.trustify.darktransfertdata.model.Customer;
import com.trustify.darktransfertdata.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@AllArgsConstructor
@RestController
public class CustomerController {

    private CustomerService customerService;

    /**
     * Get all customer with each operation own self
     * @return The list customer with each operation
     */
    @GetMapping("/")
    public Iterable<Customer> findAllTransactionWithCustomer(){
        return customerService.findAllTransactionWithCustomer();
    }

    @GetMapping("/customer/{id}")
    public Customer findById(@PathVariable Long id){
        return this.customerService.findById(id);
    }

    @GetMapping("/customer")
    public Customer findByIdentify(@RequestParam String identify){
        if(identify == null)
            return null;
        return this.customerService.findByIdentify(identify);
    }

    @GetMapping("/customer/")
    public List<Customer> findByOperationType(@RequestParam Operation type){
        return this.customerService.findByOperationType(type);
    }
/*
    @GetMapping("/customer/transaction")
    public List<Customer> findByOperationStatus(@RequestParam boolean status){
        return this.customerService.findByOperationStatus(status);
    }*/

    @GetMapping("/customer/transaction")
    public List<Customer> findByOperationStatus(
            @RequestParam(required = false) Operation type,
            @RequestParam(required = false) boolean status
    ){
        return this.customerService.findAllByOperationTypeAndOperationStatus(type,status);
    }

}
