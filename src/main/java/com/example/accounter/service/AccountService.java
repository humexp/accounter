package com.example.accounter.service;

import com.example.accounter.component.PersistenceUtil;
import com.example.accounter.entity.Account;
import com.example.accounter.entity.Transfer;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class AccountService {
    private static final AccountService ACCOUNT_SERVICE = new AccountService();

    public static AccountService instance() {
        return ACCOUNT_SERVICE;
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

    public void deleteAccount(Long id) {
        EntityManager em = PersistenceUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Account account = em.find(Account.class, id);
            if (account != null) {
                em.remove(account);
            } else {
                throw new NullPointerException("No such account with id=" + id);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Account> getAccountList() {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.createQuery("select a from Account a", Account.class).getResultList();
    }
}
