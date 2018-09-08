package com.example.accounter.service;

import com.example.accounter.component.PersistenceUtil;
import com.example.accounter.entity.Account;
import com.example.accounter.entity.Transfer;

import javax.persistence.EntityManager;
import java.util.List;

public class AccountService {
    public static AccountService instance() {
        return new AccountService();
    }

    public Account createAccount(Account account) {
        EntityManager em = PersistenceUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return account;
    }

    public Account getAccount(Long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.find(Account.class, id);
    }

    public List<Account> getAccountList() {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.createQuery( "select a from Account a", Account.class).getResultList();
    }

    public Transfer executeTransfer(Transfer transfer) {
        return null;
    }
}
