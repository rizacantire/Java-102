package com.patika.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnector {
    private Connection connection = null;
    public Connection connection(){
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
        try {
            this.connection = DriverManager.getConnection(Config.DB_URL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.connection;
    }
    public static Connection getInstace(){
        DbConnector db = new DbConnector();
        return db.connection();
    }
}
