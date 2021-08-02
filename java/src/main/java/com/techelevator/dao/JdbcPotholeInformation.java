package com.techelevator.dao;

import com.techelevator.model.PotholeInformation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;

public class JdbcPotholeInformation implements PotholeInformationDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcPotholeInformation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ArrayList<PotholeInformation> getPotholes() {
        String sql = "SELECT * FROM pothole_information";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        ArrayList<PotholeInformation> potholes = new ArrayList<>();
        try {
            while(result.next()) {
                potholes.add(mapRowToPotholeInformation(result));
            }
        }  catch (DataAccessException ex) {
            System.out.println(ex.getMessage());
        }
        return potholes;
    }

    private PotholeInformation mapRowToPotholeInformation(SqlRowSet row) {
        PotholeInformation potholes = new PotholeInformation();
        potholes.setDateCreated(row.getDate("date_created").toLocalDate());
        potholes.setPotholeId(row.getInt("id"));
        potholes.setLongitude(row.getDouble("longitude"));
        potholes.setLatitude(row.getDouble("latitude"));
        potholes.setSeverity(row.getInt("severity"));

        return potholes;
    }
}
