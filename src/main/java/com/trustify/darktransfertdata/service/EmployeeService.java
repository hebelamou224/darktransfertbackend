package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.model.Employee;
import com.trustify.darktransfertdata.repository.AgencyRepository;
import com.trustify.darktransfertdata.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private AgencyRepository agencyRepository;

    public Employee save(Employee employee) {

        employee.setDateRegister(Instant.now());

        return this.employeeRepository.save(employee);
    }

    public Employee findByUsername(String username) {
        Optional<Employee> employeeOptional = this.employeeRepository.findByUsername(username);
        if (employeeOptional.isPresent()) {
            System.out.println(employeeOptional.get());
            return employeeOptional.get();
        }
        throw new RuntimeException("Ce nom d'utilisateur ne correspond pas a un employee");
    }

    public Employee put(Employee employee, String username, String identityAgency) {

        Optional<Agency> agency = this.agencyRepository.findByIdentify(identityAgency);
        if (this.employeeRepository.findByUsername(username).isPresent()) {
            if (agency.isPresent()) {
                // employee.setAgency(agency.get());
                employee.setUsername(username);
                employee.setId(this.employeeRepository.findByUsername(username).get().getId());
                employee.setDateRegister(Instant.now());

                return this.employeeRepository.save(employee);
            } else
                throw new RuntimeException("Cet identifiant ne correspond pas à une agence");
        } else
            throw new RuntimeException("Cet employee n'existe pas");
    }

    public Employee updateInformation(Employee employee) {
        Optional<Employee> employeeOptional = this.employeeRepository.findByUsername(employee.getUsername());
        if (employeeOptional.isPresent()) {
            System.out.println(employeeOptional.get());
            System.out.println(employee);
            Employee employeeUpdate = employeeOptional.get();
            employeeUpdate.setAddress(employee.getAddress());
            employeeUpdate.setFullname(employee.getFullname());
            employeeUpdate.setTelephone(employee.getTelephone());
            employeeUpdate.setPassword(employee.getPassword());

            return this.employeeRepository.save(employeeUpdate);
        }
        return null;
    }

    public Employee findEmployeeByUsernameAndPassword(String username, String password) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findEmployeeByUsernameAndPassword(username, password);
        return optionalEmployee.orElse(null);
    }

    public Iterable<Employee> findAll() {
        return this.employeeRepository.findAll();
    }


}
