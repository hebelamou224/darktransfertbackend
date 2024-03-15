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

    public Employee put(Employee employee, String username, String identityAgency) {

        Optional<Agency> agency = this.agencyRepository.findByIdentify(identityAgency);
        if (this.employeeRepository.findByUsername(username).isPresent()) {
            if (agency.isPresent()) {
                employee.setAgency(agency.get());
                employee.setUsername(username);
                employee.setId(this.employeeRepository.findByUsername(username).get().getId());
                employee.setDateRegister(Instant.now());

                System.out.println(employee);

                return this.employeeRepository.save(employee);
            } else
                throw new RuntimeException("Cet identifiant ne correspond pas à une agence");
        } else
            throw new RuntimeException("Cet employee n'existe pas");
    }

    public Iterable<Employee> findAll() {
        return this.employeeRepository.findAll();
    }


}
