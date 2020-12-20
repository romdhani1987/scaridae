package fr.romdhani.scaridae;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.Address;
import fr.romdhani.scaridae.core.orm.Company;
import fr.romdhani.scaridae.core.orm.CompanyStatus;


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
                company.setName("Romdhani");
                company.setSiret("dfgsdf");
                Address address = new Address("hello", "cc", "eee", "fqsf");
                CompanyStatus companyStatus = new CompanyStatus("SARL", "Company a SARL");
                company.setAddress(address);
                company.setCompanyStatus(companyStatus);
                entityManager.persist(company);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
