package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.model.Employee;
import com.trustify.darktransfertdata.model.Partner;
import com.trustify.darktransfertdata.service.PartnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/partner",
        produces = {
                "application/json",
        })
public class PartnerController {

    private PartnerService partnerService;

    @PostMapping(value = "/add", consumes = {
            "application/json",
            "application/x-www-form-urlencoded;charset=UTF-8"
    },
            produces = {
                    "application/json",
                    "application/x-www-form-urlencoded;charset=UTF-8"
            })
    public String save(Partner partner) {
        return this.partnerService.save(partner);
    }

    @GetMapping("/")
    public Iterable<Partner> findByAll() {
        return this.partnerService.findByAll();
    }

    @GetMapping("")
    public List<Partner> findAllByFullnameContainingOrTelephoneContainingOrderByIdDesc(@RequestParam String searchValue) {
        return this.partnerService.findAllByFullnameContainingOrTelephoneContainingOrderByIdDesc(searchValue, searchValue, searchValue);
    }

    @PutMapping(value = "/addEmployeeToAgency/{username}", consumes = {
            "application/json",
            "application/x-www-form-urlencoded;charset=UTF-8"
    })
    public Partner addEmployeeForAnAgency(
            Employee employee,
            @PathVariable String username,
            @RequestParam String identify
    ) {
        return this.partnerService.addEmployeeForAnAgency(username, identify, employee);
    }

    @PutMapping(value = "/addAgencyForPartner/{username}", consumes = {
            "application/json",
            "application/x-www-form-urlencoded;charset=UTF-8"
    })
    public Partner addAgencyForPartner(
            Agency agency,
            @PathVariable String username
    ) {
        return this.partnerService.addAgencyForPartner(username, agency);
    }


}
