package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.Agency;
import com.trustify.darktransfertdata.service.AgencyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/agency")
public class AgencyController {

    private AgencyService agencyService;

    @PostMapping("/add")
    public Agency save(@RequestBody Agency agency) {
        return this.agencyService.save(agency);
    }

    @GetMapping
    public Iterable<Agency> findAll() {
        return this.agencyService.findAll();
    }

}
