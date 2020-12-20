package fr.romdhani.scaridae;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.database.DBSession;
import fr.romdhani.scaridae.core.database.HibernateUtil;
import fr.romdhani.scaridae.core.orm.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Creates and displays the main view.
 *
 * @author aromdhani
 */
public class ScaridaeView {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        try {
            System.out.println("*** Start Scaridae ***");
            DBEntityManager.getInstance().doInTransaction((entityManager) -> {
                String qlQuery = "SELECT u FROM Company u";
                Query query = entityManager.createQuery(qlQuery);
                List<Company> users = query.getResultList();
                users.stream().forEach((user) -> {
                    System.out.println(user.getName());
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
