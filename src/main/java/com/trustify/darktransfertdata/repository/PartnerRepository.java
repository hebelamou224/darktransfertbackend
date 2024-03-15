package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.model.Partner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, Long> {
    public Optional<Partner> findByUsername(String username);
}
