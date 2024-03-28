package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.Operation;
import com.trustify.darktransfertdata.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public Optional<Customer> findByIdentify(String identify);
    public List<Customer> findAllByOperation_Type(Operation type);
    public List<Customer> findAllByOperation_Status(boolean status);
    public  List<Customer> findAllByOperationTypeAndOperationStatus(Operation type, boolean status) ;

    public List<Customer> findAllByOrderByOperationDateModifyDesc();

    //List<Customer> findByOperationDateModifyBetween(Instant startOfDay, Instant endOfDay);
    List<Customer> findAllByFullnameContainingOrFullnameReceverContainingOrIdentifyContainingOrderByOperationDateModifyDesc(String fullname, String fullnameRecever, String identify);

    public Optional<Customer> findFirstByOrderByOperationDateModifyDesc();
    //public List<Customer> findTopNByOrderByOperationDateModifyDesc(int n);
}
