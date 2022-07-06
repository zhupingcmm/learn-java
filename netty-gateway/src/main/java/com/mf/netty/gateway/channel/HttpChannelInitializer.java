package com.mf.netty.gateway.channel;

import com.mf.netty.gateway.inbound.HttpInboundHandler;
import com.mf.netty.gateway.inbound.HttpTrackingHandler;
import com.mf.netty.gateway.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private HttpTrackingHandler httpTrackingHandler;

    @Autowired
    private HttpOutboundHandler httpOutboundHandler;

    @Autowired
    private HttpInboundHandler httpInboundHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024*1024));
        pipeline.addLast(httpTrackingHandler);
        pipeline.addLast(httpOutboundHandler);
        pipeline.addLast(httpInboundHandler);
    }
}
