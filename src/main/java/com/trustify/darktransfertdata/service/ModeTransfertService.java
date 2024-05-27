package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.ModeTransfert;
import com.trustify.darktransfertdata.repository.ModeTransfertRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModeTransfertService {

    private ModeTransfertRepository modeTransfertRepository;

    public ModeTransfert save(ModeTransfert modeTransfert) {
        return this.modeTransfertRepository.save(modeTransfert);
    }

    public Iterable<ModeTransfert> findAll() {
        return this.modeTransfertRepository.findAll();
    }
}
