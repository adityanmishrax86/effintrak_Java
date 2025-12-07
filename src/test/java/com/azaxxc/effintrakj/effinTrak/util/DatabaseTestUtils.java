package com.azaxxc.effintrakj.effinTrak.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseTestUtils {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Clears all data from the database
     * Note: Order matters due to foreign key constraints
     */
    @Transactional
    public void clearDatabase() {
        entityManager.createNativeQuery("TRUNCATE TABLE refresh_tokens CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE transfers CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE expenses CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE incomes CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE bank_accounts CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE categories CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE users CASCADE").executeUpdate();
        entityManager.flush();
        entityManager.clear();
    }

    /**
     * Clears a specific table
     */
    @Transactional
    public void clearTable(String tableName) {
        entityManager.createNativeQuery("TRUNCATE TABLE " + tableName + " CASCADE").executeUpdate();
        entityManager.flush();
        entityManager.clear();
    }

    /**
     * Executes a native SQL query
     */
    @Transactional
    public void executeNativeQuery(String sql) {
        entityManager.createNativeQuery(sql).executeUpdate();
        entityManager.flush();
    }

    /**
     * Flushes and clears the persistence context
     */
    public void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}

