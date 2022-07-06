package com.mf.netty.gateway.inbound;

import io.netty.channel.ChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;


@Component
@ChannelHandler.Sharable
public class HttpTrackingHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpTrackingHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("HttpTrackingHandler {}::", msg.toString());
        super.channelRead(ctx, msg);
    }
}
