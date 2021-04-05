package fr.romdhani.scaridae.core.database.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.Address;
import fr.romdhani.scaridae.core.orm.Company;
import fr.romdhani.scaridae.core.orm.Service;
import fr.romdhani.scaridae.core.orm.UserFunction;

public class V1_1__populate_database extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        //Address
        Address address= new Address("13 rue de Vauban ","Mundolsheim","France","67600");
        //Company
        Company company = new Company("RS2D","RS2D1234RS2D");
        company.setAddress(address);
        //UserFunction
        UserFunction software = new UserFunction("Software engineer", "Software engineer");
        UserFunction softwareManager = new UserFunction("Software Manager", "Software Manager");
        UserFunction electronicEngineer = new UserFunction("Electronic Engineer", "Electronic Engineer");
        UserFunction MRIEngineer = new UserFunction("MRI Engineer", "MRI Engineer");
        UserFunction NMREngineer = new UserFunction("NMR Engineer", "NMR Engineer");
        UserFunction FinanceController = new UserFunction("Finance Controller", "Finance Controller");
        UserFunction administrativeManager = new UserFunction("Administrative Manager", "Administrative Manager");
        UserFunction cto = new UserFunction("Cto", "Chief Technical Officer");
        UserFunction salesManager = new UserFunction("Sales Manager", "Sales Manager");
        // services
        Service commercial = new Service("Sales Service", "Sales Service");
        Service electronic = new Service("Electronic Service", "Electronic Service");
        Service IT = new Service("IT Service", "IT Service");
        Service softwareService = new Service("Software Service", "Software Service");
        Service direction = new Service("Direction Service", "Direction Service");
        Service administrative = new Service("Administrative Service", "Administrative Service");
        Service logistics = new Service("Logistics Service", "Logistics Service");
        Service install = new Service("Install Service", "Install Service");
        //Save in database
        DBEntityManager.getInstance().persistEntities(company,address,softwareService, commercial, electronic, direction,
                IT, administrative, logistics, install, software, softwareManager, electronicEngineer, MRIEngineer,
                NMREngineer, FinanceController, administrativeManager, cto, salesManager);
    }
}