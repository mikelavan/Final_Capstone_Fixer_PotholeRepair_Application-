package com.techelevator.controller;

import com.techelevator.dao.JdbcPotholeInformation;
import com.techelevator.dao.PotholeInformationDAO;
import com.techelevator.model.PotholeInformation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class PotholeInformationController {
//    private PotholeInformationService potholeService;
    private JdbcPotholeInformation jdbcPotholeInformation;

//    public PotholeInformationController(){};
    public PotholeInformationController(JdbcPotholeInformation jdbcPotholeInformation) {
        this.jdbcPotholeInformation = jdbcPotholeInformation;
    }

    @RequestMapping(path="/potholes", method = RequestMethod.GET)
    public ArrayList<PotholeInformation> listPotholes() {
        return jdbcPotholeInformation.getPotholes();
    }
}
