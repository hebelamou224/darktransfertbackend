package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.ModeTransfert;
import com.trustify.darktransfertdata.service.ModeTransfertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("mode")
public class ModeTransfertController {
    private ModeTransfertService modeTransfertService;

    @PostMapping()
    public ModeTransfert save(@RequestBody ModeTransfert modeTransfert) {
        return this.modeTransfertService.save(modeTransfert);
    }

    @GetMapping()
    public Iterable<ModeTransfert> findAll() {
        return this.modeTransfertService.findAll();
    }

    @PutMapping()
    public ModeTransfert put(@RequestBody ModeTransfert modeTransfert) {
        return this.modeTransfertService.save(modeTransfert);
    }

}
