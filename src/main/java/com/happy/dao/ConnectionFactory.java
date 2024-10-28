package com.happy.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String url = "jdbc:mysql://localhost:3306/techblog";
    private static final String username = "root";
    private static final String password = "Jayant@2005";
    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static Connection conn;

    public static Connection getConn() {
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
