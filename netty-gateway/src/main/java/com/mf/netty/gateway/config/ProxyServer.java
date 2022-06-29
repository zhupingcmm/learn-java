package com.mf.netty.gateway.config;

import lombok.Getter;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyServer {
    private static Logger logger = LoggerFactory.getLogger(ProxyServer.class);
    private static final String ZK_ROOT = "/server";

    @Getter
    private Map<String, String> proxyServers = new HashMap<>();
    private static ProxyServer instance;
    private ProxyServer() {}
    public static ProxyServer getInstance() {
            if (instance == null) {
                synchronized (ProxyServer.class) {
                    if (instance == null) {
                        instance = new ProxyServer();
                    }
                }
            }
            return instance;
    }

    public void initConnectToZk () {
        logger.info("start to connect zk");
        ZkClient zkClient = new ZkClient("localhost:2181");
        zkClient.setZkSerializer(new ZkSerializer());
        if (!zkClient.exists(ZK_ROOT)) {
            throw new RuntimeException("zk not contain the root node");
        }
        List<String> children = zkClient.getChildren(ZK_ROOT);
        children.forEach(child -> {
            String childPath = ZK_ROOT + "/" + child;

            String url = zkClient.readData(childPath);
            proxyServers.put(childPath, url);

            IZkDataListener dataListener = new IZkDataListener() {
                @Override
                public void handleDataChange(String s, Object o) throws Exception {
                    logger.info("zk server have a change action, we must delete {} in local", s);
                    proxyServers.put(s, (String) o);
                }

                @Override
                public void handleDataDeleted(String s) throws Exception {
                    logger.info("zk server have a delete action, we must delete {} in local", s);
                    proxyServers.remove(s);
                }
            };
            zkClient.subscribeDataChanges(childPath,dataListener);
        });
    }
}
