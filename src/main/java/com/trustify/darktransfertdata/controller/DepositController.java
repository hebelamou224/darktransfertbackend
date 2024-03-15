package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.Customer;
import com.trustify.darktransfertdata.model.Operation;
import com.trustify.darktransfertdata.service.CustomerService;
import com.trustify.darktransfertdata.service.DepositService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RestController
public class DepositController {

    private CustomerService customerService;
    private DepositService depositService;

    /**
     * Post deposit in database
     * @param customer Is a personal which do the operation
     * @return customer and operation if is done
     */
    @PostMapping("/deposit")
    public Customer deposit(@RequestBody Customer customer){
        return depositService.deposit(customer);
    }

    /**
     * Get all operation saved in database
     * @return The lists operation
     */
    @GetMapping(path = "op")
    public Iterable<Operation> findAllTransaction(){
        return depositService.findAll();
    }

    /**
     * Get all customer with each operation own self
     * @return The list customer with each operation
     */
    @GetMapping("/")
    public Iterable<Customer> findAllTransactionWithCustomer(){
        return customerService.findAllTransactionWithCustomer();
    }

    /**
     * Find an operation by code
     * @param code for searching operation
     * @return the operation entities if is existed or null else
     */
    @GetMapping("/op/{code}")
    public Operation findByCode(@PathVariable String code){
        return depositService.findByCode(code);
    }

    @PostMapping("/withdrawal/{code}")
    public Customer withdrawal(@RequestBody Customer customer, @PathVariable String code){
        return this.depositService.withdrawal(customer,code);
    }

    @GetMapping("/operation/{id}")
    public Operation findByOperation(@PathVariable Long id){
        return depositService.findById(id);
    }


}
