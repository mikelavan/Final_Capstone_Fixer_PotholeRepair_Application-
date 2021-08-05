package com.techelevator.dao;

import com.techelevator.model.PotholeInformation;

import java.util.ArrayList;

public interface PotholeInformationDAO {
    ArrayList<PotholeInformation> getPotholes();

    void deletePothole(int id);

    void createReport(PotholeInformation pothole);

    void updateSeverity(PotholeInformation pothole);
}
