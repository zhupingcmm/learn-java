package com.mf.java.core.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public Connection getConnection() {
        CglibProxy proxy = new CglibProxy();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return  proxy.getProxy(DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useInformationSchema=false&charset=utf8mb4&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true", "root", "1234"));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
