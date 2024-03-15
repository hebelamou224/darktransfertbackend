package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.model.Employee;
import com.trustify.darktransfertdata.model.Partner;
import com.trustify.darktransfertdata.service.PartnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/partner")
public class PartnerController {

    private PartnerService partnerService;

    @PostMapping("/add")
    public Partner save(@RequestBody Partner partner) {
        return this.partnerService.save(partner);
    }

    @GetMapping("/")
    public Iterable<Partner> findByAll() {
        return this.partnerService.findByAll();
    }

    @PutMapping("/addEmployeeToAgency/{username}")
    public Partner addEmployeeForAnAgency(
            @RequestBody Employee employee,
            @PathVariable String username,
            @RequestParam String identify
    ) {
        return this.partnerService.addEmployeeForAnAgency(username, identify, employee);
    }

    @PutMapping("/addAgencyForPartner/{username}")
    public Partner addAgencyForPartner(
            @RequestBody Agency agency,
            @PathVariable String username
    ) {
        return this.partnerService.addAgencyForPartner(username, agency);
    }


}
