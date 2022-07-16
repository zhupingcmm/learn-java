package com.mf.java.core.jdbc;

import java.sql.*;

public class JdbcOperation {

    private DataSourceManager dataSourceManager;

    public JdbcOperation () {
        dataSourceManager = new DataSourceManager(10);
    }
    public void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        try {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void release(Connection connection, PreparedStatement preparedStatement){
        try {
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  int updateItem(String sql){
        return updateSql(sql);
    }

    public  int deleteItem(String sql){
        return updateSql(sql);
    }

    public  int addItem(String sql){
        return updateSql(sql);
    }
    public ResultSet getItem(String sql) throws SQLException {
        Connection connection = dataSourceManager.getConnection();
        PreparedStatement prepareStatement = null;
        ResultSet result = null;

        try {
            connection.setAutoCommit(false);
            prepareStatement = connection.prepareStatement(sql);
//            prepareStatement.setInt(1, 2);

            result = prepareStatement.executeQuery();
            connection.commit();
            return result;
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            release(connection, prepareStatement, result);
        }
    }


    public void batchUpdate(String sql) {
        Connection connection = dataSourceManager.getConnection();
        PreparedStatement preparedStatement = null;
        int batchSize = 100;
        int size = 1000000;
        try {
            connection.setAutoCommit(false);
            preparedStatement =connection.prepareStatement(sql);
            for (int i = 0; i < size; i++) {
                preparedStatement.setString(1, "zp" + i);
                preparedStatement.setString(2, "1234" + i);
                preparedStatement.setInt(3,2);
                preparedStatement.setInt(4, 1);
                if ((i % batchSize) == 0 && i >= 100) {
                    preparedStatement.addBatch();
                    preparedStatement.executeBatch();
                    connection.commit();
                }
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
//            try {
//                connection.commit();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
            release(connection, preparedStatement);
        }

    }

    private int updateSql(String sql)  {
        Connection connection = dataSourceManager.getConnection();
        PreparedStatement prepareStatement = null;
        try {
            connection.setAutoCommit(false);
            prepareStatement =connection.prepareStatement(sql);

            for (int i = 0; i < 10; i++) {
                prepareStatement.setString(1, "zp");
                prepareStatement.setString(2, "1234");
                prepareStatement.setInt(3,2);
                prepareStatement.setInt(4, 1);
                prepareStatement.addBatch();
            }
            prepareStatement.addBatch();
            return prepareStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            release(connection, prepareStatement);
        }
    }

}
