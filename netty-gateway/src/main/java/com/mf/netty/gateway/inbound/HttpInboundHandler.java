package com.mf.netty.gateway.inbound;


import com.alibaba.fastjson.JSON;
import com.mf.netty.gateway.config.ProxyServer;
import com.mf.netty.gateway.route.Router;
import com.mf.netty.gateway.route.impl.RouterImpl;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private ThreadPoolExecutor service;

    public HttpInboundHandler () {
        initThreadPool();
    }

    private void initThreadPool() {
        int cores = Runtime.getRuntime().availableProcessors();
        service = new ThreadPoolExecutor(
                cores, cores,
                5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
                );
    }




    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        String method = String.valueOf(fullHttpRequest.method());
        switch (method) {
            case "POST":
                service.submit(() -> fetchPost(fullHttpRequest, ctx));
                break;
            case "PATCH":
                service.submit(() -> fetchPatch(fullHttpRequest, ctx));
                break;
            case "DELETE":
                service.submit(() -> fetchDelete(fullHttpRequest, ctx));
                break;
            default:
                service.submit(() -> fetchGet(fullHttpRequest, ctx));
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    private void fetchPost(final FullHttpRequest request, final ChannelHandlerContext ctx) {

    }

    private void fetchPatch(final FullHttpRequest request, final ChannelHandlerContext ctx) {

    }

    private void fetchDelete(final FullHttpRequest request, final ChannelHandlerContext ctx) {

    }

    private void fetchGet(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
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
