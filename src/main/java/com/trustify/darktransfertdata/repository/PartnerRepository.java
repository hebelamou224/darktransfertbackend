package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.model.Partner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, Long> {
    Optional<Partner> findByUsername(String username);

    Optional<Partner> findByUsernameAndPassword(String username, String password);

    List<Partner> findAllByFullnameContainingOrTelephoneContainingOrUsernameContainingOrderByIdDesc(String fullname, String telephone, String username);
}
