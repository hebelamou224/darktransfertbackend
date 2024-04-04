package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Action;
import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.model.Employee;
import com.trustify.darktransfertdata.model.Partner;
import com.trustify.darktransfertdata.repository.AgencyRepository;
import com.trustify.darktransfertdata.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PartnerService {

    private PartnerRepository partnerRepository;
    private AgencyRepository agencyRepository;
    private ActionService actionService;

    /**
     * when the application is first opened,
     * this function allows you to create
     * the administrator and the main agency
     */
    public void createAgencyPrincipalAtOpenFirstApp() {
        if (this.partnerRepository.count() == 0) {
            //Partner
            String username = "darktransfert";
            String fullname = "Dark Transfert";
            String address = "Guinée Conakry";
            String telephone = "";
            Instant dateRegister = Instant.now();
            List<Agency> agencies = new ArrayList<>();

            //Agency
            String identify = Instant.now().toString();
            String name = "Agence Principal";
            String description = "L'une des agence principal de dark transfert";
            String lieu = "Conakry";
            double account = 0.0;
            List<Employee> employees = new ArrayList<>();
            Agency agency = new Agency(1L, identify, name, description, lieu, account, employees);

            //Employee
            String usernameEmp = "dartransfert";
            String fullnameEmp = "Dark Transfert";
            String addressEmp = "Conakry Guinée";
            String telephoneEmp = "";
            Instant dateRegisterEmp = Instant.now();
            String role = "ADMIN";
            String identifyAgency = agency.getIdentify();
            String password = "DARKADMIN";
            Employee employee = new Employee(1L, usernameEmp, fullnameEmp, addressEmp, telephoneEmp, dateRegisterEmp, role, identifyAgency, password);

            //Register informations
            Partner partner = new Partner(null, username, fullname, address, telephone, dateRegister, agencies);
            Partner parterMain = this.partnerRepository.save(partner);
            this.addAgencyForPartner(parterMain.getUsername(), agency);
            this.addEmployeeForAnAgency(parterMain.getUsername(), agency.getIdentify(), employee);
        }
    }

    /**
     * Add a partner to the application
     *
     * @param partner The partner to add
     * @return Returns a success message if the information is saved
     * otherwise returns a message indicating the error in saving
     */
    public String save(Partner partner) {
        Optional<Partner> optionalPartner = this.partnerRepository.findByUsername(partner.getUsername());
        if (optionalPartner.isPresent())
            return "username_existe";

        partner.setDateRegister(Instant.now());
        this.partnerRepository.save(partner);
        return "succes";
    }

    /**
     *Add an employee to an agency
     * @param usernamePartner the partner's username
     * @param identifyAgency agency ID
     * @param employee Employee to add
     * @return Return a partner with all these agencies and employee of each agency
     */
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
                employee.setIdentifyAgency(identifyAgency);
                employee.setPassword("");
                agency.getEmployees().add(employee);
                agencies.set(index, agency);
                partner.setAgencies(agencies);
                return this.partnerRepository.save(partner);
            }
            throw new RuntimeException("Cette agence n'existe pas pour ce partnaire");
        }
        throw new RuntimeException("Cet identifiant ne coresspond a un partenaire");
    }

    /**
     * Add an agency for a partner
     * @param username the partner's username
     * @param agency the agency to add
     * @return returns agency information after registration
     */
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


    /**
     * search the list of agencies that belong to a partner through their username
     * @param username the partner's username
     * @return returns the list of partner agencies if the latter exists
     */
    public List<Agency> findByUsernameAgencies(String username) {

        List<Agency> agencyIterable;
        Optional<Partner> optionalPartner = this.partnerRepository.findByUsername(username);
        if (optionalPartner.isPresent()) {
            agencyIterable = optionalPartner.get().getAgencies();
            return agencyIterable;
        }
        throw new RuntimeException("Le nom d'utilsateur ne correspons pas a un partenaire");
    }

    /**
     * deposit transaction into an account
     *
     * @param usernamePartner the partner's username so the agency belongs to it
     * @param identifyAgency  agency ID
     * @param amount          the deposit amount
     * @return agency information if the deposit has been made otherwise returned null
     */
    public Agency depositOnAccountAgency(String usernamePartner, String identifyAgency, double amount, Long idSource) {
        Optional<Partner> optionalPartner = partnerRepository.findByUsername(usernamePartner);
        if (optionalPartner.isPresent()) {
            Partner partner = optionalPartner.get();
            List<Agency> agencies = partner.getAgencies();
            int i;
            for (i = 0; i < agencies.size(); i++) {
                Agency agency = agencies.get(i);
                if (agency.getIdentify().equals(identifyAgency)) {
                    agency.setAccount(agency.getAccount() + amount);
                    agencies.set(i, agency);
                    break;
                }
            }
            partner.setAgencies(agencies);
            partnerRepository.save(partner);

            String description;
            String typeAction;
            Long idAction = agencies.get(i).getId();
            if (partner.getUsername().equals("darktransfert")) {
                description = "Rechargement d'un compte d'une agence principale de la somme de " + amount + " GNF";
                typeAction = "ENTREE AGENCE";
            } else {
                description = "Rechargement d'un compte d'une agence de la somme de " + amount + " GNF";
                typeAction = "DEPOT AGENCE";
            }
            this.actionService.registerAction(description, typeAction, idAction, idSource);


            return agencies.get(i);
        }
        return null;
    }


    /**
     * List of all partners registered in the application
     * @return Return the list of partners
     */
    public Iterable<Partner> findByAll() {
        return this.partnerRepository.findAll();
    }

    public List<Partner> findAllByFullnameContainingOrTelephoneContainingOrderByIdDesc(String fullname, String telephone, String username) {
        return this.partnerRepository.findAllByFullnameContainingOrTelephoneContainingOrUsernameContainingOrderByIdDesc(fullname, telephone,username);
    }

}
