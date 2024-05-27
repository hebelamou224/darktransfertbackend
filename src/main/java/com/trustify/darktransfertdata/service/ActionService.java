package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Action;
import com.trustify.darktransfertdata.repository.ActionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ActionService {
    private ActionRepository actionRepository;

    public Iterable<Action> findActionByEmployeeId(Long EmployeeId) {
        return this.actionRepository.findActionByEmployeeId(EmployeeId);
    }

    public List<Action> findAllByOrderByDateActionDesc() {
        return this.actionRepository.findAllByOrderByDateActionDesc();
    }


    public List<Action> findActionsByCustomerIdAndDate(Long EmployeeId, LocalDate today) {
        return this.actionRepository.findActionsByCustomerIdAndDate(EmployeeId, today);
    }

    public Iterable<Action> findAllByIdentifyAgency(String identifyAgency) {
        return this.actionRepository.findAllByIdentifyAgencyOrderByDateActionDesc(identifyAgency);
    }

    public void registerAction(String description, String typeAction, Long idAction, Long idSource, String identifyAgency) {
        Action action = new Action();
        action.setDateAction(LocalDate.now());
        action.setDescription(description);
        action.setTypeAction(typeAction);
        action.setIdAction(idAction);
        action.setIdSource(idSource);
        action.setIdentifyAgency(identifyAgency);
        this.actionRepository.save(action);
    }
}
