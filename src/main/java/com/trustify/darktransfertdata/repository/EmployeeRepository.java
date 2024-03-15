package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public Optional<Employee> findByUsername(String username);

}
