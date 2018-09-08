package com.example.accounter.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    private Long id;
    private String company;
    private String iban;
    private String activityStatus;

    @JsonCreator
    public Account(@JsonProperty("id") Long id,
                   @JsonProperty("company") String company,
                   @JsonProperty("iban") String iban,
                   @JsonProperty("activityStatus") String activityStatus) {
        this.id = id;
        this.company = company;
        this.iban = iban;
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

    public String getActivityStatus() {
        return activityStatus;
    }
}
