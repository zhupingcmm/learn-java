package com.mf.java.core.c3p0;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class PoolManagerTest {
    @Test
    public void testSaveUser () throws SQLException {
        System.out.println("test");

       String sql = "insert (account, name, password, email, role, status) into t_user values (?, ?, ?, ?, ?, ?)";
        Connection connection =  null;

        PreparedStatement preparedStatement = null;

        try {
            connection = PoolManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            Random random = new Random();
            preparedStatement.setString(1,random.nextInt(100) + "");
            preparedStatement.setString(2, "zp");
            preparedStatement.setString(3, "1234");
            preparedStatement.setString(4, "zp@hpe.com");
            preparedStatement.setInt(5, 1);
            preparedStatement.setInt(6, 1);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }

    }
}
