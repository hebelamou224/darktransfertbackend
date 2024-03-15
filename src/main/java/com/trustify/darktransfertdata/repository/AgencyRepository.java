package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.model.Agency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends CrudRepository<Agency, Long> {

    public Optional<Agency> findByIdentify(String identify);
}
