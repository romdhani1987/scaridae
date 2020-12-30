package fr.romdhani.scaridae.controller;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.RequestAccess;

public class RequestController {

    public boolean newAccessRequest(RequestAccess requestAccess) {
        try {
            DBEntityManager.getInstance().persistEntities(requestAccess);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void getAccessRequest() {

    }
}
