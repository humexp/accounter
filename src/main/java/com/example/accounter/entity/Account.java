package com.example.accounter.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    private Long id;
    private String company;
    private String iban;
    private String balance;
    private String activityStatus;

    public Account() {
    }

    @JsonCreator
    public Account(@JsonProperty("id") Long id,
                   @JsonProperty("company") String company,
                   @JsonProperty("iban") String iban,
                   @JsonProperty("balance") String balance,
                   @JsonProperty("activityStatus") String activityStatus) {
        this.id = id;
        this.company = company;
        this.iban = iban;
        this.balance = balance;
        this.activityStatus = activityStatus;
    }

    public Long getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getIban() {
        return iban;
    }

    public String getBalance() {
        return balance;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
