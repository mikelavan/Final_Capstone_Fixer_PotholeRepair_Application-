package com.techelevator.services;

import com.techelevator.dao.JdbcPotholeInformation;
import com.techelevator.model.PotholeInformation;

import java.util.ArrayList;

public class PotholeInformationService {
    private JdbcPotholeInformation potholeInformation;

    public PotholeInformationService() {}

    public PotholeInformationService(JdbcPotholeInformation potholeInformation) {
        this.potholeInformation = potholeInformation;
    }

    public ArrayList<PotholeInformation> list() {
        return potholeInformation.getPotholes();
    }
}
