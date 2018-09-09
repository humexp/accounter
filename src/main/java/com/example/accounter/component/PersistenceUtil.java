package com.example.accounter.component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static javax.persistence.Persistence.*;

public class PersistenceUtil {
    private static final String PERSISTENCE_UNIT_NAME = "accounter-mem";

    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
