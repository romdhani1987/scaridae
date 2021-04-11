package fr.romdhani.scaridae.controller;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.RequestAccess;
import fr.romdhani.scaridae.core.orm.UserAccount;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class RequestController {
    private static String USER_BY_LOGIN = "SELECT u FROM UserAccount u WHERE u.login = :userLogin";

    public void addAccessRequest(RequestAccess newRequest) throws Exception {
        DBEntityManager.getInstance().doInTransaction(em -> {
            UserAccount user = em.createQuery(
                    USER_BY_LOGIN, UserAccount.class)
                    .setParameter("userLogin", CurrentSession.getInstance().getLogin()).getSingleResult();
            user.addRequestAccess(newRequest);
            em.persist(newRequest);
            em.merge(user);
        });
    }

    public void RemoveRequestAccess(RequestAccess requestAccess) throws Exception {
        DBEntityManager.getInstance().removeEntities(requestAccess);
    }

    public void editRequestAccess(RequestAccess requestAccess) throws Exception {
        DBEntityManager.getInstance().doInTransaction(em -> {
            RequestAccess myRequestAccess = em.find(RequestAccess.class, requestAccess.getId());
        });
    }

    public List<RequestAccess> getAllRequestList() throws Exception {
        List<RequestAccess> requestAccessList = new ArrayList<>();
        DBEntityManager.getInstance().doInTransaction(em -> {
            Query query = em.createNamedQuery("findRequestAccessByUser", RequestAccess.class);
            requestAccessList.addAll(query.setParameter("user", CurrentSession.getInstance().getUserAccount()).getResultList());
        });
        return requestAccessList;
    }
}
