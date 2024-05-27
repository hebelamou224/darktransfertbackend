package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.model.ModeTransfert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeTransfertRepository extends CrudRepository<ModeTransfert, Long> {
}
