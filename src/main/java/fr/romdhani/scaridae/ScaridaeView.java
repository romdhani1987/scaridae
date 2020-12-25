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

                UserAccount userAccount = new UserAccount("aromdhani11", "sdcqs");
                RequestPurchase requestPurchase = new RequestPurchase("request1", "");
                RequestType requestType = new RequestType("type1", "");
                RequestStatus requestStatus = new RequestStatus("waiting", "wating to execute");
                requestPurchase.setRequestStatus(requestStatus);
                requestPurchase.setRequestType(requestType);
                ResponsePurchase responsePurchase = new ResponsePurchase("valid", "");
                requestPurchase.setResponsePurchase(responsePurchase);
                Set<RequestPurchase> requestPurchaseSet = new HashSet<>();
                requestPurchaseSet.add(requestPurchase);
                userAccount.setRequestPurchaseSet(requestPurchaseSet);
                ActionPurchase actionPurchase = new ActionPurchase("action1","action descriptyion");
                actionPurchase.setResponsePurchase(responsePurchase);
                responsePurchase.setActionPurchaseSet(Set.of(actionPurchase));
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
