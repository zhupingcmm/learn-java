package com.mf.demo.plugin;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.Executor;

@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)})
public class APlugin implements Interceptor {

    private Properties properties = new Properties();


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object o = invocation.proceed();
        System.out.println("APlugin::");
        return o;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
