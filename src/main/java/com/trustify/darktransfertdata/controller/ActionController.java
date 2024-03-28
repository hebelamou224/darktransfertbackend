package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.Action;
import com.trustify.darktransfertdata.service.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/action")
public class ActionController {
    private ActionService actionService;

    @GetMapping()
    public List<Action> findActionByEmployeeId(@RequestParam Long EmployeeId) {
        return this.actionService.findActionByEmployeeId(EmployeeId);
    }

    @GetMapping("/")
    public List<Action> findAllByOrderByDateActionDesc() {
        return this.actionService.findAllByOrderByDateActionDesc();
    }

    @GetMapping("/{EmployeeId}")
    List<Action> findActionsByCustomerIdAndDate(@PathVariable Long EmployeeId, @RequestParam(required = false) LocalDate date) {
        return this.actionService.findActionsByCustomerIdAndDate(EmployeeId, Objects.requireNonNullElseGet(date, LocalDate::now));
    }
}
