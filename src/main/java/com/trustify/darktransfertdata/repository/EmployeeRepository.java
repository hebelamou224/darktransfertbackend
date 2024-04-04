package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Optional<Employee> findByUsername(String username);

    Optional<Employee> findEmployeeByUsernameAndPassword(String username, String password);


}
