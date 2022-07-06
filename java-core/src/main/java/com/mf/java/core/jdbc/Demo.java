package com.mf.java.core.jdbc;


import java.sql.ResultSet;


public class Demo {
    public static void main(String[] args) {

        ResultSet resultSet = JdbcUtil.getItem("select * from user where id = 2");
        System.out.println(resultSet);
        JdbcUtil.addItem("insert into user values (8,'zp', 18);");
        JdbcUtil.updateItem("update user set name = 'cmm' where id = 8");
        JdbcUtil.deleteItem("delete from  user where id=8");

    }
}
