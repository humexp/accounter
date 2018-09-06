package com.example.accounter.service;

import com.example.accounter.entity.Account;
import com.example.accounter.entity.Transfer;

import java.util.List;

public class AccountService {
    public static AccountService instance() {
        return new AccountService();
    }

    public Account createAccount(Account account) {
        return null;
    }

    public Account getAccount(Long id) {
        return null;
    }

    public List<Account> getAccountList() {
        return null;
    }

    public Transfer executeTransfer(Transfer transfer) {
        return null;
    }
}
