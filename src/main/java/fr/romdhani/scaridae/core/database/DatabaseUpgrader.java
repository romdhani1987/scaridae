package fr.romdhani.scaridae.core.database;

import fr.romdhani.scaridae.controller.ConfigLoader;
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
            config.setDataSource(ConfigLoader.JDBC_PREFIX + "://" + ConfigLoader.getInstance().getHost() + ":" + ConfigLoader.getInstance().getPort() + "/" + ConfigLoader.getInstance().getDbName(), ConfigLoader.getInstance().getUser(), ConfigLoader.getInstance().getPass());
            config.setBaselineOnMigrate(true);
            config.setTable("scaridae_schema_history");
            config.setLocations(new Location("classpath:database"), new Location("classpath:fr/romdhani/scaridae/core/database/migration"));
            flyway = new Flyway(config);
        } catch (FlywayException ex) {
            logger.error("Failed to initialize Flyway! ", ex);
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