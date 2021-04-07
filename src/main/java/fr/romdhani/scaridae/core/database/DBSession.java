package fr.romdhani.scaridae.core.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Handle sessions
 *
 * @author aromdhani
 */
public class DBSession {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private DBSession() {
    }

    private static class InstanceHolder {
        public static final DBSession INSTANCE = new DBSession();
    }

    public static DBSession getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public boolean doInTransaction(Runnable runnable) {

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            runnable.run();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Do in transaction
     *
     * @param consumer
     * @return
     */
    public void doInTransaction(Consumer<Session> consumer) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            consumer.accept(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
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
        Session session = sessionFactory.openSession();
        Map<T, R> result = new HashMap<>();
        try {
            session.beginTransaction();
            for (T t : list) {
                result.put(t, func.apply(t));
            }
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            if (session != null) session.close();
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
