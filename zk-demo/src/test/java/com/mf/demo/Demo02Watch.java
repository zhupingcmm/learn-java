package com.mf.demo;

import lombok.val;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class Demo02Watch {

    private CuratorFramework client;
    @Before
    public void initClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 100);
        String connectString = "127.0.0.1:2181";
        int sessionTimeoutMs = 60 * 1000;//当前客户端会话超时时间
        int connectionTimeoutMs = 15 * 1000;//连接超时时间
        client = CuratorFrameworkFactory.newClient(connectString, sessionTimeoutMs, connectionTimeoutMs, retryPolicy);
    }

    @Test
    public void listenNode() throws Exception {
        client.start();

        System.out.println("===链接成功===");
        NodeCache nodeCache = new NodeCache(client, "/hero");

        val currentData = nodeCache.getCurrentData();
        System.out.println("当前节点数据 = " + currentData);
        // 监听开始
        nodeCache.start();
        nodeCache.getListenable().addListener(() -> {
            val data = nodeCache.getCurrentData();
            val path = data.getPath();
            System.out.println("节点名称 = " + path);
            byte[] currentDataByte = data.getData();
            System.out.println("修改后节点数据" + new String(currentDataByte));
            System.out.println("--------------->>");
        });
        Thread.sleep(100000);
    }


    @After
    public void destroy() {
        client.close();
    }


}
