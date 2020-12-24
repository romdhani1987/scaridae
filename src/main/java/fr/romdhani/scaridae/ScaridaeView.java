package fr.romdhani.scaridae;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.*;

import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


/**
 * Creates and displays the main view.
 *
 * @author aromdhani
 */
public class ScaridaeView {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        try {
            System.out.println("*** Start scaridae ***");
            DBEntityManager.getInstance().doInTransaction((entityManager) -> {
                Provider customer = new Provider();
                Company company = new Company("AmazonaA", "d14252deqf");
                customer.setCompany(company);
                entityManager.persist(company);
                entityManager.persist(customer);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
