package fr.romdhani.scaridae.core.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.configuration.ClassicConfiguration;

public class DatabaseUpgrader {
    private static final Logger logger = LogManager.getLogger(DatabaseUpgrader.class);
    private Flyway flyway;

    public DatabaseUpgrader() {
        initConfig();
    }

    private void initConfig() {
        try {
            ClassicConfiguration config = new ClassicConfiguration();
            config.setDataSource("jdbc:postgresql://127.0.0.1:5432/db2", "postgres", "postgres");
            config.setBaselineOnMigrate(true);
            config.setTable("scaridae_schema_history");
            config.setLocations(new Location("classpath:database"),new Location("classpath:fr/romdhani/scaridae/core/database/migration"));
            flyway = new Flyway(config);
        } catch (FlywayException ex) {
            logger.error("Failed to initialize Flyway! "+ ex);
        }
    }

    public void initAndMigrate() {
        flyway.migrate();
        info(flyway.info().current());
    }

    private void info(MigrationInfo migrationInfo) {
        if (migrationInfo == null) {
            logger.warn("No database found at the actual datasource!");
        } else {
            logger.info("Current scaridae database version: " + migrationInfo.getVersion());
        }
    }

}