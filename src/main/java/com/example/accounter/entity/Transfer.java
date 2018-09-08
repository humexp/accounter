package com.example.accounter.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transfer {
    private Long accountFrom;
    private Long accountTo;
    private String sum;

    @JsonCreator
    public Transfer(@JsonProperty("accountFrom") Long accountFrom,
                    @JsonProperty("accountTo") Long accountTo,
                    @JsonProperty("sum") String sum) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.sum = sum;
    }

    public Long getAccountFrom() {
        return accountFrom;
    }

    public Long getAccountTo() {
        return accountTo;
    }

    public String getSum() {
        return sum;
    }
}
