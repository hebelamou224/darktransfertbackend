package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.Operation;
import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.service.AgencyService;
import com.trustify.darktransfertdata.service.PartnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/agency")
public class AgencyController {

    private AgencyService agencyService;
    private PartnerService partnerService;

    @PostMapping("/add")
    public Agency save(@RequestBody Agency agency) {
        return this.agencyService.save(agency);
    }

    @GetMapping("/")
    public Iterable<Agency> findAll() {
        return this.agencyService.findAll();
    }

    @GetMapping("/{usernamePartner}")
    public List<Agency> findByUsernamePartnerAgencies(@PathVariable String usernamePartner) {
        return this.partnerService.findByUsernameAgencies(usernamePartner);
    }

    @PutMapping("/{identifyAgency}")
    public Agency depositOnAccountAgency(
            @PathVariable String identifyAgency,
            @RequestParam String usernamePartner,
            @RequestParam double amount,
            @RequestParam Long idSource
    ) {
        return this.partnerService.depositOnAccountAgency(usernamePartner, identifyAgency, amount, idSource);
    }

    @PutMapping(value = "/update/{identityAgency}", consumes = {
            "application/json",
            "application/x-www-form-urlencoded;charset=UTF-8"
    })
    public Agency updateAccountAgencyAfterOperationDeposit(@PathVariable String identityAgency, @RequestParam double amount) {
        return this.agencyService.updateOnAccountAgencyAfterOperationDeposit(identityAgency, amount);
    }

    @PutMapping("/updateAccount/{identityAgency}")
    public Agency updateOnAccountAgencyAfterOperation(@PathVariable String identityAgency, @RequestParam double amount, @RequestParam String type) {
        return this.agencyService.updateOnAccountAgencyAfterOperation(identityAgency, amount, type);
    }

    @PutMapping("/updateAccount/{identityAgency}/{idSource}/")
    public Agency updateOnAccountMainAgencyAfterOperationWithdrawal(@PathVariable String identityAgency, @RequestParam double amount, @PathVariable Long idSource) {
        return this.agencyService.updateOnAccountMainAgencyAfterOperation(identityAgency, amount, idSource);
    }

    @GetMapping()
    public Agency findByAgency(@RequestParam String identifyAgency) {
        return this.agencyService.findByIdentify(identifyAgency);
    }

}
