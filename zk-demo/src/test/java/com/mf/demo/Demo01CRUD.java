package com.mf.demo;

import lombok.val;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

public class Demo01CRUD {

    @Test
    public void demo() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 3);

        val client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);

        client.start();

        client.delete().forPath("/app2");
        client.create().forPath("/app2",new byte[]{'a', 'b'});
        val bytes = client.getData().forPath("/app2");
        System.out.println(new String(bytes));
        client.close();
    }
}
