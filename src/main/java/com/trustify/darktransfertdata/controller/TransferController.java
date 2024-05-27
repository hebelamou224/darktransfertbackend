package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.Transfert;
import com.trustify.darktransfertdata.service.TransfertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("transfert")
public class TransferController {

    private TransfertService transfertService;

    @PostMapping()
    public Transfert save(@RequestBody Transfert transfert) {
        transfert.setDateTransfert(LocalDateTime.now());
        return this.transfertService.save(transfert);
    }

    @GetMapping()
    public Iterable<Transfert> findAll() {
        return this.transfertService.findAll();
    }
}
