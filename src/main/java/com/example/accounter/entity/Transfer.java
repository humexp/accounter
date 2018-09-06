package com.example.accounter.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transfer {
    String accountFrom;
    String accountTo;
    String sum;

    @JsonCreator
    public Transfer(@JsonProperty("accountFrom") String accountFrom,
                    @JsonProperty("accountTo") String accountTo,
                    @JsonProperty("sum") String sum) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.sum = sum;
    }
}
