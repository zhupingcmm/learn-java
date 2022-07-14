package com.mf.java.core.jdbc;


import java.sql.ResultSet;
import java.sql.SQLException;


public class Demo {
    public static void main(String[] args) throws SQLException {

        JdbcOperation jdbcOperation = new JdbcOperation();
        ResultSet resultSet = jdbcOperation.getItem("select * from user where id = 2");
        System.out.println(resultSet);
        jdbcOperation.addItem("insert into user values (8,'zp', 18);");
        jdbcOperation.updateItem("update user set name = 'cmm' where id = 8");
        jdbcOperation.deleteItem("delete from  user where id=8");

    }
}
