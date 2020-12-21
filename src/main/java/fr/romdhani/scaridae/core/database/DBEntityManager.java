package fr.romdhani.scaridae.core.database;


import org.hibernate.SessionFactory;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Handle EntityManager
 *
 * @author aromdhani
 */
public class DBEntityManager {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private DBEntityManager() {
    }

    private static class InstanceHolder {
        public static final DBEntityManager INSTANCE = new DBEntityManager();
    }

    public static DBEntityManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public boolean doInTransaction(Runnable runnable) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            runnable.run();
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (entityManager != null) entityManager.close();
        }
    }

    /**
     * Do in transaction
     *
     * @param consumer
     * @return
     */
    public void doInTransaction(Consumer<EntityManager> consumer) throws Exception {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction tx = null;
        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            tx = entityManager.getTransaction();
            tx.begin();
            consumer.accept(entityManager);
            entityManager.flush();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
        }
    }

    /**
     * @param list
     * @param func
     * @param <T>
     * @param <R>
     * @return
     */
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

    /**
     * Close session factory
     */
    public void closeSessionFactory() {
        try {
            sessionFactory.close();
        } finally {
            if (sessionFactory != null) sessionFactory.close();
        }
    }

}
