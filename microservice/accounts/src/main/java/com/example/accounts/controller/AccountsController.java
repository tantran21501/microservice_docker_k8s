package com.example.accounts.controller;

import com.example.accounts.config.AccountsServiceConfig;
import com.example.accounts.model.*;
import com.example.accounts.service.AccountsService;
import com.example.accounts.service.client.CardsFeignClient;
import com.example.accounts.service.client.LoansFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountsController {
    private final AccountsService accountsService;
    private final AccountsServiceConfig accountsServiceConfig;

    private final CardsFeignClient cardsFeignClient;

    private final LoansFeignClient loansFeignClient;

    public AccountsController(AccountsService accountsService,
                              AccountsServiceConfig accountsServiceConfig,
                              CardsFeignClient cardsFeignClient,
                              LoansFeignClient loansFeignClient) {
        this.accountsService = accountsService;
        this.accountsServiceConfig = accountsServiceConfig;
        this.cardsFeignClient = cardsFeignClient;
        this.loansFeignClient = loansFeignClient;
    }


    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customer) {
        Accounts accounts = accountsService.findByCustomerId(customer.getCustomerId());
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }
    }

    @GetMapping("/account/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountsServiceConfig.getMsg(),
                accountsServiceConfig.getBuildVersion(),
                accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
    @PostMapping("/myCustomerDetails")
    @CircuitBreaker(name="detailsForCustomerSupportApp", fallbackMethod = "myCustomerDetailsFallBack")
    @Retry(name="retryForCustomerDetails",fallbackMethod = "myCustomerDetailsFallBack")
    public CustomerDetails myCustomerDetails(@RequestHeader("bank-correlation-id") String correlationid, @RequestBody Customer customer){
        Accounts accounts = accountsService.findByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoanDetails(correlationid,customer);
        List<Cards> cards = cardsFeignClient.getCardDetails(correlationid,customer);
        return new CustomerDetails(accounts,cards,loans);
    }

    private CustomerDetails myCustomerDetailsFallBack(@RequestHeader("bank-correlation-id") String correlationid,Customer customer, Throwable throwable){
        Accounts accounts = accountsService.findByCustomerId(customer.getCustomerId());
        List<Loans> loans =  loansFeignClient.getLoanDetails(correlationid,customer);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setLoans(loans);
        return customerDetails;
    }
    @GetMapping("/sayHello")
    @RateLimiter(name="sayHello", fallbackMethod = "sayHelloFallback")
    public String sayHello(){
        Optional<String> podName = Optional.ofNullable(System.getenv("HOSTNAME"));
        return "Helloooooooooooo !" +
                "Welcome to Kubernetes Cluster: " + podName.get();


    }
    private String sayHelloFallback(Throwable throwable){
        return "Hiiiiiiiiii !!";
    }

    @GetMapping("bulkhead")
    @Bulkhead(name="bulkhead", fallbackMethod = "bulkheadFallback")
    public String bulkhead(){
        for (int i = 0; i < 1000000; i++) {
            System.out.println("Bulk Head 1" + i);
        }
        Thread currentThread = Thread.currentThread();
        int activeThreadCount = currentThread.activeCount();
        return "BUlkhead 1: " + activeThreadCount;
    }
    @GetMapping("bulkhead1")
    @Bulkhead(name="bulkhead2", fallbackMethod = "bulkheadFallback")
    public String bulkhead1(){
        for (int i = 0; i < 10000000; i++) {
            System.out.println("Bulk Head 2" + i);
        }
        Thread currentThread = Thread.currentThread();
        int activeThreadCount = currentThread.activeCount();
        return "BUlkhead 2: " + activeThreadCount;
    }

    public String bulkheadFallback(Throwable throwable){
        return "FAILED BULKHEAD";
    }
}
