package com.techelevator.dao;

import com.techelevator.model.PotholeInformation;
import com.techelevator.model.Schedule;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JdbcPotholeInformation implements PotholeInformationDAO {

    private JdbcTemplate jdbcTemplate;

//    public JdbcPotholeInformation(){};

    public JdbcPotholeInformation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ArrayList<PotholeInformation> getPotholes() {
        String sql = "SELECT id, date_created, longitude, latitude, severity, status FROM pothole_information";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        ArrayList<PotholeInformation> potholes = new ArrayList<>();
        try {
            while(result.next()) {
                PotholeInformation potholeInformation = mapRowToPotholeInformation(result);
                potholes.add(potholeInformation);
            }
        }  catch (DataAccessException ex) {
            System.out.println(ex.getMessage());
        }
        return potholes;
    }

    @Override
    public void deletePothole(int id) {
        String sql = "DELETE FROM pothole_information WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void createReport(PotholeInformation pothole) {
        String sql = "INSERT INTO pothole_information (longitude, latitude) VALUES (?, ?);";
        jdbcTemplate.update(sql, pothole.getLongitude(), pothole.getLatitude());
    }

    @Override
    public void updateSeverity(PotholeInformation pothole) {
        String sql = "UPDATE pothole_information SET severity = ? WHERE id = ?";
        jdbcTemplate.update(sql, pothole.getSeverity(), pothole.getPotholeId());
    }

    private PotholeInformation mapRowToPotholeInformation(SqlRowSet row) {
        PotholeInformation potholes = new PotholeInformation();
        potholes.setPotholeId(row.getInt("id"));
        potholes.setDateCreated(row.getDate("date_created").toLocalDate());
        potholes.setStatus(row.getString("status"));
        potholes.setLongitude(row.getDouble("longitude"));
        potholes.setLatitude(row.getDouble("latitude"));
        potholes.setSeverity(row.getInt("severity"));

        return potholes;
    }
}
