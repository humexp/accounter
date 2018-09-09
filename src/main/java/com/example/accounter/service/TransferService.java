package com.example.accounter.service;

import com.example.accounter.component.PersistenceUtil;
import com.example.accounter.entity.Account;
import com.example.accounter.entity.Transfer;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.function.Function;

public class TransferService {
    private static final TransferService TRANSFER_SERVICE = new TransferService();

    public static TransferService instance() {
        return TRANSFER_SERVICE;
    }

    public Transfer getTransfer(Integer id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.find(Transfer.class, id);
    }

    public Transfer executeTransfer(Transfer transfer) {
        EntityManager em = PersistenceUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            BigDecimal sum = new BigDecimal(transfer.getSum());
            accountTransfer(transfer.getAccountFrom(), em, account -> new BigDecimal(account.getBalance()).subtract(sum));
            accountTransfer(transfer.getAccountTo(), em, account -> new BigDecimal(account.getBalance()).add(sum));

            em.persist(transfer);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return transfer;
    }

    private void accountTransfer(Long accountId, EntityManager em, Function<Account, BigDecimal> newBalanceClosure) {
        Account account = em.find(Account.class, accountId, LockModeType.PESSIMISTIC_WRITE);

        if (account == null) {
            throw new NullPointerException("No such account with id=" + accountId);
        }

        String newBalance = newBalanceClosure.apply(account).toPlainString();
        account.setBalance(newBalance);
        em.merge(account);
    }
}
