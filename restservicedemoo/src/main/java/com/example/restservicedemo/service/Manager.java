package com.example.restservicedemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Vuko on 26.05.2016.
 */
public abstract class Manager {
    protected static Connection connection;

    private static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";

    public Manager()
    {
        if(connection == null) {
            try {
                Class.forName("org.hsqldb.jdbcDriver" );
                connection = DriverManager.getConnection(URL);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
