package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.model.Employee;
import com.trustify.darktransfertdata.model.Partner;
import com.trustify.darktransfertdata.repository.AgencyRepository;
import com.trustify.darktransfertdata.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PartnerService {

    private PartnerRepository partnerRepository;
    private AgencyRepository agencyRepository;

    public String save(Partner partner) {
        Optional<Partner> optionalPartner = this.partnerRepository.findByUsername(partner.getUsername());
        if (optionalPartner.isPresent())
            return "username_existe";

        partner.setDateRegister(Instant.now());
        this.partnerRepository.save(partner);
        return "succes";
    }

    public Partner addEmployeeForAnAgency(String usernamePartner, String identifyAgency, Employee employee) {

        Optional<Partner> optionalPartner = this.partnerRepository.findByUsername(usernamePartner);
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
                employee.setDateRegister(Instant.now());
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

    public List<Agency> findByUsernameAgencies(String username) {

        List<Agency> agencyIterable;
        Optional<Partner> optionalPartner = this.partnerRepository.findByUsername(username);
        if (optionalPartner.isPresent()) {
            agencyIterable = optionalPartner.get().getAgencies();
            return agencyIterable;
        }
        throw new RuntimeException("Le nom d'utilsateur ne correspons pas a un partenaire");
    }

    public Iterable<Partner> findByAll() {
        return this.partnerRepository.findAll();
    }

}
