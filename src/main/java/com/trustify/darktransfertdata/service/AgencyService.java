package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.repository.AgencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgencyService {

    private AgencyRepository agencyRepository;

    public Agency save(Agency agency) {
        return this.agencyRepository.save(agency);
    }

    public Iterable<Agency> findAll() {
        return this.agencyRepository.findAll();
    }

}
