package com.example.accounter.service;

import com.example.accounter.component.PersistenceUtil;
import com.example.accounter.entity.Account;
import com.example.accounter.entity.Transfer;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

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

            Account accountFrom = em.find(Account.class, transfer.getAccountFrom());
            Account accountTo = em.find(Account.class, transfer.getAccountTo());

            BigDecimal sum = new BigDecimal(transfer.getSum());

            String accFromBalance = new BigDecimal(accountFrom.getBalance()).subtract(sum).toPlainString();
            String accToBalance = new BigDecimal(accountTo.getBalance()).add(sum).toPlainString();

            accountFrom.setBalance(accFromBalance);
            accountTo.setBalance(accToBalance);

            em.merge(accountFrom);
            em.merge(accountTo);

            em.persist(transfer);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return transfer;
    }
}
