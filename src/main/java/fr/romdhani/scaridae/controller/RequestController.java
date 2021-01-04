package fr.romdhani.scaridae.controller;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.RequestAccess;
import fr.romdhani.scaridae.core.orm.UserAccount;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author aromdhani
 */
public class RequestController {


    public <T> boolean add(T newRequest) {
        try {
            if (newRequest instanceof Serializable) {
                Serializable request = (Serializable) newRequest;
                DBEntityManager.getInstance().persistEntities(request);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addReq(RequestAccess newRequest) {
        try {
            if (newRequest instanceof Serializable) {
                DBEntityManager.getInstance().doInTransaction(em -> {
                    UserAccount user = CurrentSession.getInstance().getUserAccount();
                    user.addRequestAccess(newRequest);
                    em.persist(newRequest);
                    em.merge(user);
                });
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public <T> List<T> getList(final String reqAsString) {
        List<T> list = new ArrayList<>();
        try {
            DBEntityManager.getInstance().doInTransaction(em -> {
                Query query = em.createQuery(reqAsString);
                list.addAll(query.getResultList());
            });
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

    public List<RequestAccess> getAllRequestList() {
        List<RequestAccess> list = new ArrayList<>();
        try {
            DBEntityManager.getInstance().doInTransaction(em -> {
                Query query = em.createNamedQuery("findRequestAccessByUser", RequestAccess.class);
                list.addAll(query.setParameter("user", CurrentSession.getInstance().getUserAccount()).getResultList());
            });
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

    public List<RequestAccess> getRequestList() {
        List<RequestAccess> list = new ArrayList<>();
        try {
            list.addAll(CurrentSession.getInstance().getUserAccount().getRequestAccessSet());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

}
