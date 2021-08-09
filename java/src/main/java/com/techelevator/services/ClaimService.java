package com.techelevator.services;

import com.techelevator.dao.JdbcClaim;
import com.techelevator.model.Claim;
import org.springframework.stereotype.Service;

@Service
public class ClaimService {
    private JdbcClaim jdbcClaim;

    public ClaimService(JdbcClaim jdbcClaim){
        this.jdbcClaim = jdbcClaim;
    }

    public void makeClaim(Claim claim){
        jdbcClaim.createClaim(claim);
    }
}
