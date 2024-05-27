package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.Customer;
import com.trustify.darktransfertdata.model.Operation;
import com.trustify.darktransfertdata.service.CustomerService;
import com.trustify.darktransfertdata.service.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RestController
@RequestMapping(path = "operation", produces = {
        MediaType.APPLICATION_JSON_VALUE,
})
public class OperationController {

    private CustomerService customerService;
    private OperationService depositService;


    /**
     * Get all operation saved in database
     * @return The lists operation
     */
    @GetMapping
    public Iterable<Operation> findAllOperation(){
        return depositService.findAll();
    }

    /**
     * Find an operation by code
     * @param code for searching operation
     * @return the operation entities if is existed or null else
     */
    @GetMapping("/{code}")
    public Operation findByCode(@PathVariable String code){
        return depositService.findByCode(code);
    }

    @GetMapping("/")
    public Operation findByCodeWithdrawal(@RequestParam(required = false) String codeWithdrawal){
        return depositService.findByCodeWithdrawal(codeWithdrawal);
    }

    /**
     * Post deposit in database
     * @param customer Is a personal which do the operation
     * @return customer and operation if is done
     */
    @PostMapping(value = "/deposit", consumes = {
            "application/json",
            "application/x-www-form-urlencoded;charset=UTF-8"
    })
    public Customer deposit(
            Customer customer,
            @RequestParam double amount,
            @RequestParam Long idSource,
            @RequestParam String identifyAgency
    ) {
        return depositService.deposit(customer, amount, idSource, identifyAgency);
    }

    /**
     * Method for the withdrawal in a deposit
     * @param code for the operation deposit
     * @return The entities operation with the personal which do the withdrawal
     */
    @PutMapping(value = "/withdrawal/{code}")
    public Operation withdrawal(@PathVariable String code, @RequestParam Long idSource) {
        return this.depositService.withdrawal(code, idSource);
    }

    /**
     * Get all operation by type(DEPOSIT or WITHDRAWAL)
     * @param type Of operation
     * @return A list which contains the corresponding operation type
     */
    @GetMapping("/type/{type}")
    public List<Operation> findByType(@PathVariable com.trustify.darktransfertdata.Operation type){
        return this.depositService.findByType(type);
    }



}
