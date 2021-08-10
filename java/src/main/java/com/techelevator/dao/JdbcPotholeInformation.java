package com.techelevator.dao;

import com.techelevator.model.PotholeInformation;
import com.techelevator.model.Schedule;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JdbcPotholeInformation implements PotholeInformationDAO {

    private JdbcTemplate jdbcTemplate;

//    public JdbcPotholeInformation(){};

    public JdbcPotholeInformation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ArrayList<PotholeInformation> getPotholes() {
        String sql = "SELECT id, date_created, longitude, latitude, s.severity, s.status FROM pothole_information p " +
                "JOIN  schedule s ON p.id = s.pothole_id " +
                "ORDER BY s.severity DESC";
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
        String sql = "DELETE FROM schedule WHERE pothole_id = ?";
        jdbcTemplate.update(sql, id);

        sql = "DELETE FROM pothole_information WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void createReport(PotholeInformation pothole) {
        String sql = "INSERT INTO pothole_information (longitude, latitude) VALUES (?, ?) RETURNING id";
        Integer potholeID;
        potholeID = jdbcTemplate.queryForObject(sql, Integer.class, pothole.getLongitude(), pothole.getLatitude());

        sql = "INSERT INTO schedule (pothole_id) VALUES (?)";
        jdbcTemplate.update(sql, potholeID);

        sql = "SELECT date_created FROM pothole_information WHERE id = ?";
        Date date = jdbcTemplate.queryForObject(sql, Date.class, potholeID);

        if (date != null){
            sql = "UPDATE schedule SET date_reported = ? WHERE pothole_id = ?";
            jdbcTemplate.update(sql, date, potholeID);
        }

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
        if(row.getString("status") != null){
        potholes.setStatus(row.getString("status")); }
        potholes.setLongitude(row.getDouble("longitude"));
        potholes.setLatitude(row.getDouble("latitude"));
        potholes.setSeverity(row.getInt("severity"));

        return potholes;
    }

    @Override
    public void updatePicture(MultipartFile file, int id) throws IOException {
        SqlLobValue data = new SqlLobValue(file.getBytes());
        String sql = "UPDATE pothole_information SET picture = ? WHERE id = ?";

        jdbcTemplate.update(sql, data, id);
    }
}
