package fr.romdhani.scaridae.core.database;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutor {
    DBEntityManager dbEntityManager = DBEntityManager.getInstance();

    public <T> void persist(T object) throws Exception {
        dbEntityManager.doInTransaction(consumer -> {
            consumer.persist(object);
        });
    }

    public Object find(Object object, Long id) throws Exception {
        final Object[] obj = new Object[1];
        dbEntityManager.doInTransaction(consumer -> {
            obj[0] = consumer.find(object.getClass(), id);
        });
        return obj[0];
    }

    public <T> void remove(T object) throws Exception {
        dbEntityManager.doInTransaction(consumer -> {
            consumer.remove(object);
        });
    }


    public <T> void update(T object) throws Exception {
        dbEntityManager.doInTransaction(consumer -> {
            consumer.merge(object);
        });
    }

    public List<Object> execute(String query) throws Exception {
        List<Object> resultList = new ArrayList<>();
        dbEntityManager.doInTransaction(consumer -> {
            Query query2 = consumer.createQuery(query);
            resultList.addAll(query2.getResultList());
        });
        return resultList;
    }

}
