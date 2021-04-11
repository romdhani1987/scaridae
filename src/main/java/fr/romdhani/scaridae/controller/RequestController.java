package fr.romdhani.scaridae.controller;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.RequestAccess;
import fr.romdhani.scaridae.core.orm.UserAccount;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class RequestController {

    public void addAccessRequest(RequestAccess newRequest) throws Exception {
        DBEntityManager.getInstance().doInTransaction(em -> {
            UserAccount user = em.createQuery(
                    "SELECT u FROM UserAccount u WHERE u.login = :userLogin", UserAccount.class)
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
            RequestAccess myRequestAccess = em.createQuery(
                    "SELECT ra FROM RequestAccess ra WHERE ra.id = :id", RequestAccess.class)
                    .setParameter("id", requestAccess.getId()).getSingleResult();
            em.merge(myRequestAccess);
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
