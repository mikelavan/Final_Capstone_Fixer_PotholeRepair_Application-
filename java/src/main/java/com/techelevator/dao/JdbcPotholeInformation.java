package com.techelevator.dao;

import com.techelevator.model.PotholeInformation;
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
        String sql = "SELECT id, date_created, longitude, latitude, severity FROM pothole_information";
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

    private PotholeInformation mapRowToPotholeInformation(SqlRowSet row) {
        PotholeInformation potholes = new PotholeInformation();
        potholes.setPotholeId(row.getInt("id"));
        potholes.setDateCreated(row.getDate("date_created").toLocalDate());
        potholes.setLongitude(row.getDouble("longitude"));
        potholes.setLatitude(row.getDouble("latitude"));
        potholes.setSeverity(row.getInt("severity"));

        return potholes;
    }
}
