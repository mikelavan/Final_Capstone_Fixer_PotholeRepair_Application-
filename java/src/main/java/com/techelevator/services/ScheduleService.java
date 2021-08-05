package com.techelevator.services;

import com.techelevator.dao.JdbcPotholeInformation;
import com.techelevator.dao.JdbcSchedule;
import com.techelevator.model.PotholeInformation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScheduleService {
    private JdbcSchedule jdbcSchedule;

//    public PotholeInformationService() {}

    public ScheduleService(JdbcSchedule jdbcSchedule) {
        this.jdbcSchedule = jdbcSchedule;
    }

    public void create(int id) {
        jdbcSchedule.createSchedule(id);
    }

}