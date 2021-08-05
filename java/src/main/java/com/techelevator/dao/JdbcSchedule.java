package com.techelevator.dao;

import com.techelevator.model.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcSchedule implements ScheduleDAO {
    private JdbcTemplate jdbcTemplate;

    public JdbcSchedule(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void createSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedule (pothole_id) VALUES (?)";

   jdbcTemplate.update(sql, schedule.getPotholeId());

    }
}
