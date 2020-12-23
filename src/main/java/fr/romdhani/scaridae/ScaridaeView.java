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


                UserAccount userAccount = new UserAccount("login9", "hello");

                Project project = new Project("project1", "description");
                Set<Project> projectSet = new HashSet<>();
                projectSet.add(project);
                userAccount.setProjectSet(projectSet);

                Intervention intervention = new Intervention("intervention", "reparing");
                InterventionType interventionType = new InterventionType("type1", "");
                intervention.setInterventionType(interventionType);

                intervention.setInterventionProjectSet(projectSet);

                Set<UserAccount> userSet = new HashSet<>();
                userSet.add(userAccount);
                intervention.setInterventionUserAccountSet(userSet);


                entityManager.persist(project);
                entityManager.persist(userAccount);
                entityManager.persist(intervention);
                entityManager.persist(interventionType);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
