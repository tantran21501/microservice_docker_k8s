package com.example.loans.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_number")
    private Long loanNumber;
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="start_date")
    private String startDate;
    @Column(name="loan_type")
    private String loanType;
    @Column(name="total_loan")
    private int totalLoan;
    @Column(name="amount_paid")
    private int amountPaid;
    @Column(name="outstanding_amount")
    private int outstandingAmount;
    @Column(name="create_date")
    private String createDate;


}
