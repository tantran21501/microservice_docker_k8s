package com.example.loans.controller;

import com.example.loans.config.LoansServiceConfig;
import com.example.loans.model.Customer;
import com.example.loans.model.Loans;
import com.example.loans.model.Properties;
import com.example.loans.service.LoansService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoansController {
//    @Autowired
    private final LoansService loansService;
    private final LoansServiceConfig loansServiceConfig;
    public LoansController(LoansService loansService, LoansServiceConfig loansServiceConfig) {
        this.loansService = loansService;
        this.loansServiceConfig = loansServiceConfig;
    }

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestHeader("bank-correlation-id")String correlationId, @RequestBody Customer customer){
        List<Loans> loans = loansService.findByCustomerIdOrderByStartDateDesc(customer.getCustomerId());
        return loans;
    }
    @GetMapping("/loans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                loansServiceConfig.getMsg(), loansServiceConfig.getBuildVersion(),
                loansServiceConfig.getMailDetails(),loansServiceConfig.getActiveBranches()
        );
        String jsonStr = writer.writeValueAsString(properties);
        return jsonStr;

    }
}
