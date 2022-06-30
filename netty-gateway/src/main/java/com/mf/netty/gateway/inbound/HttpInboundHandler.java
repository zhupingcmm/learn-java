package com.mf.netty.gateway.inbound;


import com.mf.netty.gateway.config.Constants;
import com.mf.netty.gateway.config.ThreadPool;
import com.mf.netty.gateway.inbound.http.HttpCall;
import com.mf.netty.gateway.inbound.http.impl.OkHttp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        String method = String.valueOf(fullHttpRequest.method());
        HttpCall httpCall = new OkHttp();
        logger.info("start to request");
        switch (method) {
            case Constants.POST:
                ThreadPool.getInstance().getService().submit(() -> httpCall.fetchPost(fullHttpRequest, ctx));
                break;
            case Constants.PATCH:
                ThreadPool.getInstance().getService().submit(() -> httpCall.fetchPatch(fullHttpRequest, ctx));
                break;
            case Constants.DELETE:
                ThreadPool.getInstance().getService().submit(() -> httpCall.fetchDelete(fullHttpRequest, ctx));
                break;
            default:
                ThreadPool.getInstance().getService().submit(() -> httpCall.fetchGet(fullHttpRequest, ctx));
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
