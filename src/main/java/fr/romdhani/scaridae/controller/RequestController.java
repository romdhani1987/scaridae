package fr.romdhani.scaridae.controller;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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

}
