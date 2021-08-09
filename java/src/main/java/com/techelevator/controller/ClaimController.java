package com.techelevator.controller;

import com.techelevator.model.Claim;
import com.techelevator.services.ClaimService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@CrossOrigin
@Controller
public class ClaimController {

    private ClaimService claimService;

    public ClaimController(ClaimService claimService){
        this.claimService = claimService;
    }

    @RequestMapping(path="/claim", method = RequestMethod.POST)
    public void create(@Valid @RequestBody Claim claim){
        claimService.makeClaim(claim);
    }
}
