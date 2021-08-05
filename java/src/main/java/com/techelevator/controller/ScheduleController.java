package com.techelevator.controller;

import com.techelevator.model.PotholeInformation;
import com.techelevator.model.Schedule;
import com.techelevator.services.PotholeInformationService;
import com.techelevator.services.ScheduleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class ScheduleController {
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(path="/schedule", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('EMPLOYEE')")
    public void createReport(@Valid @RequestBody Schedule schedule) {
        scheduleService.create(schedule);
    }
}
