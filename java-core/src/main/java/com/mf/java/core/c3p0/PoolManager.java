package com.mf.java.core.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Getter;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolManager {

    private static PoolManager instance;

    @Getter
    private Connection connection;
    private PoolManager () {
        this.connection = initConnection();
    }

    private Connection initConnection () {
        DataSource dataSource = new ComboPooledDataSource();
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static PoolManager getInstance() {
        if (instance == null) {
            synchronized (PoolManager.class) {
                if (instance == null) {
                    instance = new PoolManager();
                }
            }
        }
        return instance;
    }
}
