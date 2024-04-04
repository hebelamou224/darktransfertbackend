package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.Operation;
import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.repository.AgencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgencyService {

    private AgencyRepository agencyRepository;
    private PartnerService partnerService;
    private ActionService actionService;

    public Agency save(Agency agency) {
        return this.agencyRepository.save(agency);
    }

    public Iterable<Agency> findAll() {
        return this.agencyRepository.findAll();
    }


    public Agency updateOnAccountAgencyAfterOperationDeposit(String identifyAgency, double amount) {
        Optional<Agency> agencyOptional = this.agencyRepository.findByIdentify(identifyAgency);
        if (agencyOptional.isPresent()) {
            agencyOptional.get().setAccount(agencyOptional.get().getAccount() + amount);
            return this.agencyRepository.save(agencyOptional.get());
        }
        return null;
    }


    /**
     * Update the agency account after each deposit or withdrawal transaction
     *
     * @param identifyAgency agency ID
     * @param amount         the amount of the transaction
     * @param type           the type of transaction is deposit or withdrawal
     * @return the information of the agency where the operation was carried out
     */
    public Agency updateOnAccountAgencyAfterOperation(String identifyAgency, double amount, String type) {
        Optional<Agency> agencyOptional = this.agencyRepository.findByIdentify(identifyAgency);
        if (agencyOptional.isPresent()) {
            if (Objects.equals(type, "DEPOSIT")) {
                agencyOptional.get().setAccount(agencyOptional.get().getAccount() + amount);
            } else {
                System.out.print("Withdrawal amount = " + amount + "GNF");
                agencyOptional.get().setAccount(agencyOptional.get().getAccount() - amount);
            }
            return this.agencyRepository.save(agencyOptional.get());
        }
        return null;
    }

    public Agency updateOnAccountMainAgencyAfterOperation(String identifyAgency, double amount, Long idSource) {
        Optional<Agency> agencyOptional = this.agencyRepository.findByIdentify(identifyAgency);
        if (agencyOptional.isPresent()) {
            agencyOptional.get().setAccount(agencyOptional.get().getAccount() - amount);

            String description = "Retrait(sortie) dans l'agence principale d'une somme de " + amount + " GNF";
            String typeAction = "RETRAIT AGENCE";
            Long idAction = agencyOptional.get().getId();
            this.actionService.registerAction(description, typeAction, idAction, idSource);
            return this.agencyRepository.save(agencyOptional.get());
        }
        return null;
    }

    /**
     * search for an agency by its identifier
     * @param identifyAgency agency ID
     * @return returns agency information if it finds else returns null
     */
    public Agency findByIdentify(String identifyAgency) {
        Optional<Agency> optionalAgency = this.agencyRepository.findByIdentify(identifyAgency);
        return optionalAgency.orElse(null);
    }

}
