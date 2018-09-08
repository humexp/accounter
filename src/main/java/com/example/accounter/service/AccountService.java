package com.example.accounter.service;

import com.example.accounter.entity.Account;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.accounter.component.PersistenceUtil.getEntityManager;

public class AccountService {
    private static final AccountService ACCOUNT_SERVICE = new AccountService();

    public static AccountService instance() {
        return ACCOUNT_SERVICE;
    }

    public Account createAccount(Account account) {
        EntityManager em = getEntityManager();

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
        EntityManager em = getEntityManager();
        return em.find(Account.class, id);
    }

    public void deleteAccount(Long id) {
        EntityManager em = getEntityManager();

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
        EntityManager em = getEntityManager();
        return em.createQuery("select a from Account a", Account.class).getResultList();
    }
}
