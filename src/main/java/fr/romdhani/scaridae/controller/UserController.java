package fr.romdhani.scaridae.controller;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.UserAccount;

import javax.persistence.Entity;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserController {

    private UserController() {
    }

    private static class InstanceHolder {
        private static final UserController INSTANCE = new UserController();
    }

    public static UserController getInstance() {
        return UserController.InstanceHolder.INSTANCE;
    }

    public boolean signIn() {
        return true;
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
}
