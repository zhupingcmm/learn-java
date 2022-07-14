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

    public ResultSet getItem(String sql) throws SQLException {
        Connection connection = dataSourceManager.getConnection();
        PreparedStatement prepareStatement = null;
        ResultSet result = null;

        try {
            connection.setAutoCommit(false);
            prepareStatement = connection.prepareStatement(sql);
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

    public  int updateItem(String sql){
        return updateSql(sql);
    }

    public  int deleteItem(String sql){
        return updateSql(sql);
    }

    public  int addItem(String sql){
        return updateSql(sql);
    }

    private int updateSql(String sql) {
        Connection connection = dataSourceManager.getConnection();
        PreparedStatement prepareStatement = null;
        try {

            prepareStatement =connection.prepareStatement(sql);
            return prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            release(connection, prepareStatement);
        }
    }

}
