package com.pattern.util;

import java.util.ResourceBundle;

/*
 * Not sure we can test this (examples use ProcessBuilder), but this may go away with docker-compose.
 */
public class EnvironmentLoader {

    public String getDatabaseUrl () {
        return getProperty("GRAPH_DATABASE_URL", "graphDatabaseUrl");
    }

    public String getDatabaseUsername() {
        return getProperty("GRAPH_DATABASE_USERNAME", "graphDatabaseUsername");
    }

    public String getDatabasePassword() {
        return getProperty("GRAPH_DATABASE_PASSWORD", "graphDatabasePassword");
    }

    private String getProperty(String environmentVariable, String propertyName) {
        String property = System.getenv(environmentVariable);
        if (property == null) {
            ResourceBundle configs = ResourceBundle.getBundle("config");
            property = configs.getString(propertyName);
        }
        return property;
    }
}
