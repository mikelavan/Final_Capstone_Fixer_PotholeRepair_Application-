package com.techelevator.controller;

import com.techelevator.dao.PotholeInformationDAO;
import com.techelevator.model.PotholeInformation;
import com.techelevator.services.PotholeInformationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PotholeInformationController {
    private PotholeInformationService potholeService;

    public PotholeInformationController(PotholeInformationService potholeService) {
        this.potholeService = potholeService;
    }

    @RequestMapping(path="/potholes", method = RequestMethod.GET)
    public ArrayList<PotholeInformation> listPotholes() {
        return potholeService.list();
    }
}
