package fr.romdhani.scaridae;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.*;

import java.util.HashSet;
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
                Company company = new Company();
                company.setName("fake-2");
                company.setSiret("siret123555");
                Address address = new Address("9c rue de giessen", "Sélestat", "france", "67600");
                CompanyStatus companyStatus = new CompanyStatus("SARL", "Company a SARL");
                company.setAddress(address);
                company.setCompanyStatus(companyStatus);
                Service service = new Service("Software", "Software development");
                entityManager.persist(service);
                UserGroup userGroup = new UserGroup("Software", "Software development");
                service.setCompany(company);
                userGroup.setService(service);
                Address userAddress = new Address("9a rue de giessen", "Sélestat", "france", "67600");
                UserAccount userAccount = new UserAccount("login", "fvsdfsfd");
                userAccount.setAddress(userAddress);
                userAccount.setGroup(userGroup);
                userAccount.setService(service);


                UserType userTypeInt = new UserType("intern", "personnel");
                userTypeInt.setUserType(userAccount);

                UserType userTypeEx = new UserType("Extern", "personnel");
                userTypeEx.setUserType(userAccount);
                Set<UserType> set = new HashSet<>();
                set.add(userTypeEx);
                set.add(userTypeInt);
                userAccount.setUserTypeSet(set);

                UserFunction userFunction = new UserFunction("commercial", "Instrument");
                userFunction.setUserFunction(userAccount);

                entityManager.persist(userAccount);
                entityManager.persist(userAddress);
                entityManager.persist(userTypeInt);
                entityManager.persist(userTypeEx);
                entityManager.persist(userFunction);

                entityManager.persist(service);
                entityManager.persist(userGroup);
                entityManager.persist(company);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
