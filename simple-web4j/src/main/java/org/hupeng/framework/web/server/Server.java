package org.hupeng.framework.web.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : hupeng
 * @date : 2020/8/3 14:02
 */
@Slf4j
public class Server {

    private final ServerBootstrap serverBootstrap = new ServerBootstrap();

    private final EventLoopGroup boss = new NioEventLoopGroup();

    private final EventLoopGroup work = new NioEventLoopGroup();

    private final int port;

    public Server(final int port){
        this.port = port;
    }

    public void start() {

        serverBootstrap.group(boss, work);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                final ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new HttpServerCodec());
                pipeline.addLast(new HttpObjectAggregator(32*1024*1024));
                pipeline.addLast(new ServerHandler());
            }
        });
        try {
            ChannelFuture f = serverBootstrap.bind(port).sync();
            log.info("服务器启动成功：{}",port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("服务器启动失败："+port,e);
        } finally {
            shutdown();
        }
    }

    public void shutdown(){
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

}
