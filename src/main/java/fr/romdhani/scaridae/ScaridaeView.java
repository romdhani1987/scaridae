package fr.romdhani.scaridae;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.*;

import javax.persistence.TypedQuery;
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
                UserAccount userAccount = new UserAccount("aromdhani1251", "sdcqs");
                RequestQuality requestPurchase = new RequestQuality("requestaccess", "");
                RequestType requestType = new RequestType("type1", "");
                RequestStatus requestStatus = new RequestStatus("waiting", "wating to execute");
                requestPurchase.setRequestStatus(requestStatus);
                requestPurchase.setRequestType(requestType);
                ResponseQuality responsePurchase = new ResponseQuality("valid", "");
                requestPurchase.setResponseQuality(responsePurchase);
                Set<RequestQuality> requestPurchaseSet = new HashSet<>();
                requestPurchaseSet.add(requestPurchase);
                userAccount.setRequestQualitySet(requestPurchaseSet);
                ActionQuality actionPurchase = new ActionQuality("action1", "action descriptyion");
                actionPurchase.setResponseQuality(responsePurchase);
                responsePurchase.setActionQualitySet(Set.of(actionPurchase));
                entityManager.persist(requestPurchase);
                entityManager.persist(requestStatus);
                entityManager.persist(requestType);
                entityManager.persist(responsePurchase);
                entityManager.persist(userAccount);
                entityManager.persist(actionPurchase);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
