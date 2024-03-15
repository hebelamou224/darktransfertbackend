package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.model.Employee;
import com.trustify.darktransfertdata.model.Partner;
import com.trustify.darktransfertdata.repository.AgencyRepository;
import com.trustify.darktransfertdata.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PartnerService {

    private PartnerRepository partnerRepository;
    private AgencyRepository agencyRepository;

    public Partner save(Partner partner) {
        return this.partnerRepository.save(partner);
    }

    public Partner addEmployeeForAnAgency(String username, String identifyAgency, Employee employee) {

        Optional<Partner> optionalPartner = this.partnerRepository.findByUsername(username);
        if (optionalPartner.isPresent()) {
            Partner partner = optionalPartner.get();
            Optional<Agency> optionalAgency = this.agencyRepository.findByIdentify(identifyAgency);
            if (optionalAgency.isPresent()) {
                Agency agency = optionalAgency.get();
                List<Agency> agencies = partner.getAgencies();
                int index = -1;
                for (int i = 0; i < agencies.size(); i++) {
                    if (agencies.get(i).equals(agency)) {
                        index = i;
                        break;
                    }
                }
                agency.getEmployees().add(employee);
                agencies.set(index, agency);
                partner.setAgencies(agencies);
                return this.partnerRepository.save(partner);
            }
            throw new RuntimeException("Cette agence n'existe pas pour ce partnaire");
        }
        throw new RuntimeException("Cet identifiant ne coresspond a un partenaire");
    }

    public Partner addAgencyForPartner(String username, Agency agency) {

        Optional<Partner> optionalPartner = this.partnerRepository.findByUsername(username);
        if (optionalPartner.isPresent()) {
            Partner partner = optionalPartner.get();
            List<Agency> agencies = partner.getAgencies();
            agencies.add(agency);
            partner.setAgencies(agencies);

            return this.partnerRepository.save(partner);
        }
        throw new RuntimeException("Cet identifiant ne coresspond a un partenaire");
    }


    public Iterable<Partner> findByAll() {
        return this.partnerRepository.findAll();
    }

}
