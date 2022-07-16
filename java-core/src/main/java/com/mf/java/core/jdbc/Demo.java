package com.mf.java.core.jdbc;


import java.sql.ResultSet;
import java.sql.SQLException;


public class Demo {
    public static void main(String[] args) throws SQLException, InterruptedException {

        JdbcOperation jdbcOperation = new JdbcOperation();
//        ResultSet resultSet = jdbcOperation.getItem("select * from t_user");
//        System.out.println(resultSet);
//        System.out.println(resultSet);
        Thread t1 = new Thread(() -> {
            jdbcOperation.batchUpdate("insert into t_user (account, password, role, status) values (?,?,?,?);");
        });

        t1.start();
        t1.join();

//        jdbcOperation.updateItem("update user set name = 'cmm' where id = 8");
//        jdbcOperation.deleteItem("delete from  user where id=8");

    }
}
