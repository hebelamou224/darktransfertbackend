package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.model.Transfert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertRepository extends CrudRepository<Transfert, Long> {
}
