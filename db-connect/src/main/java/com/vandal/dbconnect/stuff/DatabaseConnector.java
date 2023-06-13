package com.vandal.dbconnect.stuff;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String URL_PROPERTY = "db.url";
    private static final String USERNAME_PROPERTY = "db.username";
    private static final String PASSWORD_PROPERTY = "db.password";

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = DatabaseConnector.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream == null) {
                throw new RuntimeException("Unable to find " + PROPERTIES_FILE);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading " + PROPERTIES_FILE, e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty(URL_PROPERTY);
        String username = properties.getProperty(USERNAME_PROPERTY);
        String password = properties.getProperty(PASSWORD_PROPERTY);

        return DriverManager.getConnection(url, username, password);
    }

}
