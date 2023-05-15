package com.mf.demo;

import com.mf.demo.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisExample {

    @Test
    public void test() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        //3.工厂对象Factory打开SqlSession会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.SqlSession会话对象执行SQL语句，selectList(命名空间+查询语句唯一标 识)
        List<User> users = sqlSession.selectList("test.findAll");
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> users = userMapper.findAll();
        //5.打印查询结果
        for (User u : users) { System.out.println(u); }
        //6.关闭SqlSession会话
        sqlSession.close();
    }
}
