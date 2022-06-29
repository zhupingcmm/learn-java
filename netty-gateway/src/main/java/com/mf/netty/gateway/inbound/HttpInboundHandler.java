package com.mf.netty.gateway.inbound;


import com.alibaba.fastjson.JSON;
import com.mf.netty.gateway.config.ZkSerializer;
import com.mf.netty.gateway.route.Router;
import com.mf.netty.gateway.route.impl.RouterImpl;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import okhttp3.*;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);

    private static final String ZK_ROOT = "/server";

    private ThreadPoolExecutor service;
    private Map<String, String> proxyServers = new HashMap<>();

    public HttpInboundHandler () {
        initThreadPool();
        initConnectToZk();
    }

    private void initThreadPool() {
        int cores = Runtime.getRuntime().availableProcessors();
        service = new ThreadPoolExecutor(
                cores, cores,
                5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
                );
    }


    private void initConnectToZk () {
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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        fetchGet(fullHttpRequest, ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    private void fetchGet(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        Router router = new RouterImpl();
        String backendUrl = router.route(proxyServers) + fullHttpRequest.uri();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(backendUrl)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error(" call backend is failed", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handleResponse(fullHttpRequest, ctx, response);

            }
        });

    }


    private void handleResponse(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, Response backendResponse) throws IOException {
        String value = String.valueOf(JSON.parseObject(backendResponse.body().string(), com.mf.common.api.Response.class));
        FullHttpResponse response = null;
        try {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
        } catch (Exception e) {
            logger.error("system error");
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
                ctx.flush();
            }
        }


    }
}
