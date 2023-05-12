package com.example.loans.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="mobile_number")
    private String mobileNumber;
    @Column(name="create_date")
    private String createDate;

}
