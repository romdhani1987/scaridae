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

                Address userAddress = new Address("9a rue de giessen", "Sélestat", "france", "67600");
                UserAccount userAccount = new UserAccount("login1", "fvsdfsfd");
                userAccount.setAddress(userAddress);

                Project project = new Project("project1", "description");
                Set<Project> projectSet = new HashSet<>();
                projectSet.add(project);
                userAccount.setProjectSet(projectSet);
                entityManager.persist(project);
                entityManager.persist(userAccount);
                entityManager.persist(userAddress);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
