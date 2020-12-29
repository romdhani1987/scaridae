package fr.romdhani.scaridae.controller;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.Service;
import fr.romdhani.scaridae.core.orm.UserFunction;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInitializer {
    //TODO to oad from configurations file
    private boolean isLoaded = false;

    private static class InstanceHolder {
        private static final DatabaseInitializer DATABASE_INITIALIZER = new DatabaseInitializer();
    }

    private DatabaseInitializer() {
    }

    public static DatabaseInitializer getInstance() {
        return InstanceHolder.DATABASE_INITIALIZER;
    }

    private void loadUserFunctions() throws Exception {
        UserFunction software = new UserFunction("Software engineer", "Software engineer");
        UserFunction softwareManager = new UserFunction("Software Manager", "Software Manager");
        UserFunction electronicEngineer = new UserFunction("Electronic Engineer", "Electronic Engineer");
        UserFunction MRIEngineer = new UserFunction("MRI Engineer", "MRI Engineer");
        UserFunction NMREngineer = new UserFunction("NMR Engineer", "NMR Engineer");
        UserFunction FinanceController = new UserFunction("Finance Controller", "Finance Controller");
        UserFunction administrativeManager = new UserFunction("Administrative Manager", "Administrative Manager");
        UserFunction cto = new UserFunction("Cto", "Chief Technical Officer");
        UserFunction salesManager = new UserFunction("Sales Manager", "Sales Manager");
        DBEntityManager.getInstance().persistEntities(software, softwareManager, electronicEngineer, MRIEngineer,
                NMREngineer, FinanceController, administrativeManager, cto, salesManager);
    }

    private void loadServices() throws Exception {
        Service commercial = new Service("Sales Service", "Sales Service");
        Service electronic = new Service("Electronic Service", "Electronic Service");
        Service IT = new Service("IT Service", "IT Service");
        Service software = new Service("Software Service", "Software Service");
        Service direction = new Service("Direction Service", "Direction Service");
        Service administrative = new Service("Administrative Service", "Administrative Service");
        Service logistics = new Service("Logistics Service", "Logistics Service");
        Service install = new Service("Install Service", "Install Service");
        DBEntityManager.getInstance().persistEntities(software, commercial, electronic, direction,
                IT, administrative, logistics, install);
    }

    public void load() throws Exception {
        if (isLoaded) {
            loadServices();
            loadUserFunctions();
        }
    }

    public synchronized List<UserFunction> getFunctions() {
        List<UserFunction> userFunctionList = new ArrayList<>();
        try {
            DBEntityManager.getInstance().doInTransaction(em -> {
                TypedQuery<UserFunction> query = em.createQuery(
                        "SELECT u FROM UserFunction u", UserFunction.class);
                userFunctionList.addAll(query.getResultList());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userFunctionList;

    }

    public synchronized List<Service> getServices() {
        List<Service> serviceList = new ArrayList<>();
        try {
            DBEntityManager.getInstance().doInTransaction(em -> {
                TypedQuery<Service> query = em.createQuery(
                        "SELECT s FROM Service s", Service.class);
                serviceList.addAll(query.getResultList());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceList;

    }
}
