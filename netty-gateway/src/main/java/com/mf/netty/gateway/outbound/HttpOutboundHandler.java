package com.mf.netty.gateway.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpOutboundHandler extends ChannelOutboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(HttpOutboundHandler.class);
    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        logger.debug("***HttpOutboundHandler***");
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        FullHttpResponse fullHttpResponse = (FullHttpResponse) msg;
        fullHttpResponse.headers().set("mao", "cmm");
        ctx.writeAndFlush(fullHttpResponse);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        super.flush(ctx);
    }
}
