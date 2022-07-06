package com.mf.java.core.jdbc;

import java.sql.*;

public class JdbcUtil {

    public static void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        try {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void release(Connection connection, PreparedStatement preparedStatement){
        try {
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getItem(String sql){
        MyConnection myConnection = new MyConnection();
        Connection connection = myConnection.getConnection();
        PreparedStatement prepareStatement = null;
        ResultSet result = null;

        try {
            connection.setAutoCommit(false);
            prepareStatement = connection.prepareStatement(sql);
            result = prepareStatement.executeQuery();
            connection.commit();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            release(connection, prepareStatement, result);
        }
    }

    public static int updateItem(String sql){
        MyConnection myConnection = new MyConnection();
        Connection connection = myConnection.getConnection();
        PreparedStatement prepareStatement = null;
        try {

            prepareStatement = connection.prepareStatement(sql);
            return prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            release(connection, prepareStatement);
        }
    }

    public static int deleteItem(String sql){
        MyConnection myConnection = new MyConnection();
        Connection connection = myConnection.getConnection();
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


    public static int addItem(String sql){
        MyConnection myConnection = new MyConnection();
        Connection connection = myConnection.getConnection();
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
