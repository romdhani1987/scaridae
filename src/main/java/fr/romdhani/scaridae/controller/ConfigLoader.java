package fr.romdhani.scaridae.controller;

import lombok.Getter;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class ConfigLoader {

    private static final Logger logger = LogManager.getLogger(ConfigLoader.class);

    public static final String JDBC_PREFIX = "jdbc:postgresql";
    private String host;
    private String port;
    private String user;
    private String pass;
    private String dbName;
    private String dbDriver;
    private String dbDialect;

    private ConfigLoader() {
    }

    private static class InstanceHolder {
        public static final ConfigLoader INSTANCE = new ConfigLoader();
    }

    public static ConfigLoader getInstance() {
        return ConfigLoader.InstanceHolder.INSTANCE;
    }

    public void load() {
        try {
            Properties prop = readPropertiesFile("src/main/resources/config.properties");
            host = prop.getProperty("db.host");
            port = prop.getProperty("db.port");
            user = prop.getProperty("db.user");
            pass = prop.getProperty("db.pass");
            dbName = prop.getProperty("db.name");
            dbDriver = prop.getProperty("db.driver");
            dbDialect = prop.getProperty("db.dialect");
        } catch (IOException e) {
            logger.error("Unable to read config file: " + e);
        }
    }

    private Properties readPropertiesFile(@NonNull String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException ioe) {
            logger.error("Unable to read properties from file: " + ioe);
        } finally {
            fis.close();
        }
        return prop;
    }
}
