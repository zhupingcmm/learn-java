package com.mf.java.core.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class DataSourceManager {

    private static final String URL = "jdbc:mysql://localhost:3306/test?useInformationSchema=false&charset=utf8mb4&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
    private static final String username = "root";
    private static final String password = "1234";

    private int poolSize = 10;

    private LinkedList<Connection> queue = new LinkedList<>();



    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public DataSourceManager () {

    }

    public DataSourceManager (int poolSize) {
        this.poolSize = poolSize;
        initPool();
    }


    public LinkedList<Connection> getQueue () {
        return queue;
    }

    public int getPoolSize () {
        return queue.size();
    }
    public void initPool () {
        for (int i = 0; i < poolSize; i++) {
          Connection connection = createConnection();
          queue.addLast(connection);
        }
    }



    public Connection getConnection() {
        if (queue.isEmpty()) {
            throw new RuntimeException("data source poll is empty!!");
        }
        return queue.removeFirst();
    }

    public Connection createConnection() {
        CglibProxy proxy = new CglibProxy();
        try {
            return  proxy.getProxy(DriverManager.getConnection(URL, username, password));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
