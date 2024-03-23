package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.Employee;
import com.trustify.darktransfertdata.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/add")
    public Employee save(@RequestBody Employee employee) {
        return this.employeeService.save(employee);
    }

    @GetMapping("/")
    public Iterable<Employee> findAll() {
        return this.employeeService.findAll();
    }

    @PutMapping("/put/{username}")
    public Employee put(
            @RequestBody Employee employee,
            @PathVariable String username,
            @RequestParam String identify
    ) {
        return this.employeeService.put(employee, username, identify);
    }

    @GetMapping("/{username}")
    public Employee findByUsername(@PathVariable String username) {
        return this.employeeService.findByUsername(username);
    }

    @GetMapping
    public Employee findEmployeeByUsernameAndPassword(
            @RequestParam String username,
            @RequestParam String password) {
        return this.employeeService.findEmployeeByUsernameAndPassword(username, password);
    }

}
