package fr.romdhani.scaridae;

import fr.romdhani.scaridae.core.database.HibernateUtil;
import fr.romdhani.scaridae.core.orm.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Company company=new Company();
            company.setName("RS2D");
            company.setSiret("sfsesdv11242 ");
            session.save(company);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
