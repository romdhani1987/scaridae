package fr.romdhani.scaridae.core.database;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Handle EntityManager
 *
 * @author aromdhani
 */
public class DBEntityManager {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Logger logger = LogManager.getLogger(DBEntityManager.class);
    private DBEntityManager() {
    }

    private static class InstanceHolder {
        public static final DBEntityManager INSTANCE = new DBEntityManager();
    }

    public static DBEntityManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void doInTransaction(Runnable runnable) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction tx = null;
        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            tx = entityManager.getTransaction();
            tx.begin();
            runnable.run();
            entityManager.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
            if (sessionFactory != null) sessionFactory.close();
        }
    }

    /**
     * Do in transaction
     *
     * @param consumer
     */
    public void doInTransaction(Consumer<EntityManager> consumer) throws Exception {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            transaction = entityManager.getTransaction();
            transaction.begin();
            consumer.accept(entityManager);
            entityManager.flush();
            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            throw new IOException("Failed to accept in transaction: " + ex.getMessage());
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
            if (entityManager != null) entityManager.close();
        }
    }

    /**
     * Get t in transaction
     *
     * @param supplier
     * @return t
     */
    public <T> T getInTransaction(Supplier<T> supplier) throws Exception {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            transaction = entityManager.getTransaction();
            transaction.begin();
            T t = supplier.get();
            entityManager.flush();
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new IOException("Failed to get from transaction: " + e.getMessage());
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
            if (entityManager != null) entityManager.close();
        }
    }

    public void persistEntities(Serializable... entities) throws Exception {
        DBEntityManager.getInstance().doInTransaction(em -> {
            for (Serializable entity : entities) {
                em.persist(entity);
            }
        });
    }

    public void removeEntities(Serializable... entities) throws Exception {
        DBEntityManager.getInstance().doInTransaction(em -> {
            for (Serializable entity : entities) {
                em.remove(entity);
            }
        });
    }

    public void mergeEntities(Serializable... entities) throws Exception {
        DBEntityManager.getInstance().doInTransaction(em -> {
            for (Serializable entity : entities) {
                em.merge(entity);
            }
        });
    }

    public <T, R> Map<T, R> doInTransaction(List<T> list, Function<T, R> func) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        Map<T, R> result = new HashMap<>();
        try {
            entityManager.getTransaction().begin();
            for (T t : list) {
                result.put(t, func.apply(t));
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            if (entityManager != null) entityManager.close();
        }
    }

    public void closeSessionFactory() {
        try {
            sessionFactory.close();
        } finally {
            if (sessionFactory != null) sessionFactory.close();
        }
    }

}
