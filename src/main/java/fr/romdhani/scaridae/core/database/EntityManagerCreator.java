package fr.romdhani.scaridae.core.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class EntityManagerCreator {

    private EntityManagerCreator() {
    }

    public Optional<EntityManager> createEntityOpt() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "persistence.xml");
        EntityManager entityManager = emf.createEntityManager();
        return entityManager != null ? Optional.of(entityManager) : Optional.empty();
    }

    private static class InstanceHolder {
        public static final EntityManagerCreator INSTANCE = new EntityManagerCreator();
    }

    public static EntityManagerCreator getInstance() {
        return InstanceHolder.INSTANCE;
    }

}
