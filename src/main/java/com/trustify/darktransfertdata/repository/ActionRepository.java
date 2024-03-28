package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.model.Action;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActionRepository extends CrudRepository<Action, Long> {
    @Query(value = "SELECT * FROM Action WHERE id_source = :EmployeeId ORDER BY id DESC", nativeQuery = true)
    List<Action> findActionByEmployeeId(Long EmployeeId);

    @Query(value = "SELECT * FROM Action WHERE id_source = :EmployeeId AND DATE(date_action) = :today ORDER BY id DESC", nativeQuery = true)
    List<Action> findActionsByCustomerIdAndDate(Long EmployeeId, LocalDate today);

    List<Action> findAllByOrderByDateActionDesc();

}
