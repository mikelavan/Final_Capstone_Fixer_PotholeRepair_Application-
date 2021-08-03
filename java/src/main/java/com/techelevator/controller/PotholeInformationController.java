package com.techelevator.controller;

import com.techelevator.dao.JdbcPotholeInformation;
import com.techelevator.dao.PotholeInformationDAO;
import com.techelevator.model.PotholeInformation;
import com.techelevator.services.PotholeInformationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
