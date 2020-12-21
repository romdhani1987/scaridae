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
                Company company = new Company();
                company.setName("rrr");
                company.setSiret("Aymen");
                Address address = new Address("hello", "cc", "eee", "fqsf");
                CompanyStatus companyStatus = new CompanyStatus("SARL", "Company a SARL");
                company.setAddress(address);
                company.setCompanyStatus(companyStatus);
                Service service = new Service("Software", "Software development");
                entityManager.persist(service);
                UserGroup userGroup = new UserGroup("Software", "Software development");
                service.setCompany(company);
                userGroup.setService(service);
                entityManager.persist(service);
                entityManager.persist(userGroup);
                entityManager.persist(company);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
