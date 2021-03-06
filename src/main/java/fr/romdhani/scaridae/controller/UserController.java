package fr.romdhani.scaridae.controller;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.UserAccount;
import fr.romdhani.scaridae.utils.email.GeneratePlainPassword;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author aromdhani
 */
public class UserController {

    private UserController() {
    }

    private static class InstanceHolder {
        private static final UserController INSTANCE = new UserController();
    }

    public static UserController getInstance() {
        return UserController.InstanceHolder.INSTANCE;
    }

    public synchronized boolean signIn(String login, String password) {
        List<UserAccount> userAccountList = getUserAccount(login);
        if (userAccountList.isEmpty()) return false;
        UserAccount userAccount = userAccountList.get(0);
        if (GeneratePlainPassword.generate(userAccount.getPasswordHash()).equals(password)) {
            CurrentSession.getInstance().setLogin(login);
            CurrentSession.getInstance().setUserAccount(userAccount);
            return true;
        } else {
            CurrentSession.getInstance().setLogin(null);
            CurrentSession.getInstance().setUserAccount(null);
            return false;
        }
    }

    public void signup(Serializable... entities) {
        try {
            DBEntityManager.getInstance().doInTransaction(em -> {
                for (Serializable entity : entities) {
                    em.persist(entity);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private synchronized List<UserAccount> getUserAccount(String login) {
        final List<UserAccount> results = new ArrayList<>();
        try {
            DBEntityManager.getInstance().doInTransaction(em -> {
                TypedQuery<UserAccount> query = em.createQuery(
                        "SELECT u FROM UserAccount u WHERE u.login = :userLogin", UserAccount.class);
                results.addAll(query.setParameter("userLogin", login).getResultList());

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public synchronized List<UserAccount> getUserAccountByLogin(String login) throws Exception {
        final List<UserAccount> results = new ArrayList<>();
        DBEntityManager.getInstance().doInTransaction(em -> {
            TypedQuery<UserAccount> query = em.createQuery(
                    "SELECT u FROM UserAccount u WHERE u.login = :userLogin", UserAccount.class);
            results.addAll(query.setParameter("userLogin", login).getResultList());
        });
        return results;
    }

    public synchronized List<UserAccount> getAllUserAccounts() {
        final List<UserAccount> results = new ArrayList<>();
        try {
            DBEntityManager.getInstance().doInTransaction(em -> {
                TypedQuery<UserAccount> query = em.createQuery(
                        "SELECT u FROM UserAccount u", UserAccount.class);
                results.addAll(query.getResultList());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public synchronized boolean isLoginAvailable(String login) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        try {
            DBEntityManager.getInstance().doInTransaction(em -> {
                TypedQuery<UserAccount> query = em.createQuery(
                        "SELECT u FROM UserAccount u WHERE u.login = :userLogin", UserAccount.class);
                List<UserAccount> results = query.setParameter("userLogin", login).getResultList();
                if (results.size() == 0) {
                    atomicBoolean.set(true);
                } else {
                    atomicBoolean.set(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atomicBoolean.get();
    }

    public void exit() {
        DBEntityManager.getInstance().closeSessionFactory();
        System.exit(0);
    }
}
