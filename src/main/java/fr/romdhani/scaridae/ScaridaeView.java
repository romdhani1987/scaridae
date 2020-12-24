package fr.romdhani.scaridae;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.*;


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

                Provider provider = new Provider();
                ProviderIncident providerIncident = new ProviderIncident();
                providerIncident.setProvider(provider);

                CustomerIncident customerIncident = new CustomerIncident();
                Customer customer = new Customer();
                customerIncident.setCustomer(customer);

                Company company = new Company("AmazonaAddoof", "d14252deqf");
                customer.setCompany(company);

                entityManager.persist(providerIncident);
                entityManager.persist(provider);
                entityManager.persist(company);
                entityManager.persist(customer);
                entityManager.persist(customerIncident);
                entityManager.persist(company);
                entityManager.persist(customer);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
