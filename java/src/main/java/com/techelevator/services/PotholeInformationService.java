package com.techelevator.services;

import com.techelevator.dao.JdbcPotholeInformation;
import com.techelevator.model.PotholeInformation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PotholeInformationService {
    private JdbcPotholeInformation potholeInformation;

//    public PotholeInformationService() {}

    public PotholeInformationService(JdbcPotholeInformation potholeInformation) {
        this.potholeInformation = potholeInformation;
    }

    public ArrayList<PotholeInformation> list() {
        return potholeInformation.getPotholes();
    }

    public void delete(int id) { potholeInformation.deletePothole(id); }

    public void create(PotholeInformation pothole) { potholeInformation.createReport(pothole); }
    
}
