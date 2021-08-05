package com.techelevator.controller;

import com.techelevator.dao.JdbcPotholeInformation;
import com.techelevator.dao.PotholeInformationDAO;
import com.techelevator.model.PotholeInformation;
import com.techelevator.services.PotholeInformationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class PotholeInformationController {
      private PotholeInformationService potholeService;
//    private JdbcPotholeInformation jdbcPotholeInformation;

//    public PotholeInformationController(){};
    public PotholeInformationController(PotholeInformationService potholeService) {
        this.potholeService = potholeService;
    }

    @RequestMapping(path="/potholes", method = RequestMethod.GET)
    public ArrayList<PotholeInformation> listPotholes() {
        ArrayList<PotholeInformation> potholes = potholeService.list();
        return potholes;
    }

    @RequestMapping(path="/potholes/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('EMPLOYEE')")
    public void deletePothole(@PathVariable int id) {
        potholeService.delete(id);
    }

    @RequestMapping(path="/potholes", method = RequestMethod.POST)
    @PreAuthorize("hasRole('EMPLOYEE')")
    public void createReport(@Valid @RequestBody PotholeInformation pothole) {
        potholeService.create(pothole);
    }
}
