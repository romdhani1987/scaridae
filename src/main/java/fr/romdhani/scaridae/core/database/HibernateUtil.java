package fr.romdhani.scaridae.core.database;

import java.util.Properties;

import fr.romdhani.scaridae.core.orm.Address;
import fr.romdhani.scaridae.core.orm.Company;
import fr.romdhani.scaridae.core.orm.CompanyStatus;
import fr.romdhani.scaridae.core.orm.Service;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://127.0.0.1:5432/sca");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "postgres");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Company.class);
                configuration.addAnnotatedClass(CompanyStatus.class);
                configuration.addAnnotatedClass(Address.class);
                configuration.addAnnotatedClass(Service.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}