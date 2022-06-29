package com.mf.netty.gateway.inbound.http.impl;

import com.mf.netty.gateway.config.ProxyServer;
import com.mf.netty.gateway.inbound.http.HttpCall;
import com.mf.netty.gateway.route.Router;
import com.mf.netty.gateway.route.impl.RouterImpl;
import com.mf.netty.gateway.utlis.ResponseUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class OkHttp implements HttpCall {
    private static Logger logger = LoggerFactory.getLogger(OkHttp.class);
    @Override
    public void fetchPost(FullHttpRequest request, ChannelHandlerContext ctx) {

    }

    @Override
    public void fetchPatch(FullHttpRequest request, ChannelHandlerContext ctx) {

    }

    @Override
    public void fetchDelete(FullHttpRequest request, ChannelHandlerContext ctx) {

    }

    @Override
    public void fetchGet(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        Map<String,String> proxyServers = ProxyServer.getInstance().getProxyServers();
        Router router = new RouterImpl();
        String backendUrl = router.route(proxyServers) + fullHttpRequest.uri();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .header("mao", "zp")
                .url(backendUrl)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error(" call backend is failed", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseUtil.handleResponse(fullHttpRequest, ctx, response);

            }
        });
    }
}
