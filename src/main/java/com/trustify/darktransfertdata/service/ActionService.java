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

    public List<Action> findActionByEmployeeId(Long EmployeeId) {
        return this.actionRepository.findActionByEmployeeId(EmployeeId);
    }

    public List<Action> findAllByOrderByDateActionDesc() {
        return this.actionRepository.findAllByOrderByDateActionDesc();
    }


    public List<Action> findActionsByCustomerIdAndDate(Long EmployeeId, LocalDate today) {
        return this.actionRepository.findActionsByCustomerIdAndDate(EmployeeId, today);
    }

    public void registerAction(String description, String typeAction, Long idAction, Long idSource) {
        Action action = new Action();
        action.setDateAction(LocalDate.now());
        action.setDescription(description);
        action.setTypeAction(typeAction);
        action.setIdAction(idAction);
        action.setIdSource(idSource);
        this.actionRepository.save(action);
    }
}