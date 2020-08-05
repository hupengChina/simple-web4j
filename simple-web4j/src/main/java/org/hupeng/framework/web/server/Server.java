package org.hupeng.framework.web.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : hupeng
 * @date : 2020/8/3 14:02
 */
public class Server {

    private static final Logger log = LoggerFactory.getLogger(Server.class);

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
