package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.repository.AgencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AgencyService {

    private AgencyRepository agencyRepository;
    private PartnerService partnerService;

    public Agency save(Agency agency) {
        return this.agencyRepository.save(agency);
    }

    public Iterable<Agency> findAll() {
        return this.agencyRepository.findAll();
    }

    public Agency depositOnAccountAgency(String usernamePartner, String identifyAgency, double amount) {
        return this.partnerService.depositOnAccountAgency(usernamePartner, identifyAgency, amount);
    }

    public Agency updateOnAccountAgencyAfterOperationDeposit(String identifyAgency, double amount) {
        Optional<Agency> agencyOptional = this.agencyRepository.findByIdentify(identifyAgency);
        if (agencyOptional.isPresent()) {
            agencyOptional.get().setAccount(agencyOptional.get().getAccount() + amount);
            return this.agencyRepository.save(agencyOptional.get());
        }
        return null;
    }

    public Agency findByIdentify(String identifyAgency) {
        Optional<Agency> optionalAgency = this.agencyRepository.findByIdentify(identifyAgency);
        return optionalAgency.orElse(null);
    }

}
