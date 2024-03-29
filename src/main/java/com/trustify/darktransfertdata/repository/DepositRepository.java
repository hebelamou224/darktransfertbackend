package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.model.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositRepository extends CrudRepository<Operation, Long> {
    public Optional<Operation> findByCode(String code);
    public List<Operation> findAllByType(com.trustify.darktransfertdata.Operation type);
    public Optional<Operation> findOperationByCodeWithdrawal(String codeWithdrawal);
}
