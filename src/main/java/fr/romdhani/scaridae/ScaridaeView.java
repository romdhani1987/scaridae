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
                UserAccount userAccount = new UserAccount("login4", "hello");
                userAccount.setAddress(userAddress);
                UserAccount userAccount1 = new UserAccount("login6", "hello");
                userAccount1.setAddress(userAddress);

                Project project = new Project("project1", "description");
                Set<Project> projectSet = new HashSet<>();
                projectSet.add(project);
                userAccount.setProjectSet(projectSet);


                ObjectData objectData = new ObjectData("file", "c:/file");
                Set<ObjectData> objectDataSet = new HashSet<>();
                objectDataSet.add(objectData);

                project.setObjectDataSet(objectDataSet);
                Contract contract = new Contract();

                contract.setObjectDataSet(objectDataSet);

                Set<UserAccount> usersSet = new HashSet<>();
                usersSet.add(userAccount);
                usersSet.add(userAccount1);
                contract.setUserAccountSet(usersSet);
                userAccount.setContract(contract);
                userAccount1.setContract(contract);

                entityManager.persist(contract);
                entityManager.persist(userAccount1);
                entityManager.persist(objectData);

                entityManager.persist(project);
                entityManager.persist(userAccount);
                entityManager.persist(userAddress);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
