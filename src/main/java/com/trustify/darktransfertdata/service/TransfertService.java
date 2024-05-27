package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Transfert;
import com.trustify.darktransfertdata.repository.TransfertRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransfertService {
    private TransfertRepository transfertRepository;

    public Transfert save(Transfert transfert) {
        System.out.print(transfert);
        return this.transfertRepository.save(transfert);
    }

    public Iterable<Transfert> findAll() {
        return this.transfertRepository.findAll();
    }

}
