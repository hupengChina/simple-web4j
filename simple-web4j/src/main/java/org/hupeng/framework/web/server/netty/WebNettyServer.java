package org.hupeng.framework.web.server.netty;

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
import org.hupeng.framework.common.EnvironmentConfig;
import org.hupeng.framework.common.util.StringUtil;
import org.hupeng.framework.context.ConfigurableApplicationContext;
import org.hupeng.framework.web.server.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class WebNettyServer implements WebServer {

    private static final Logger log = LoggerFactory.getLogger(WebNettyServer.class);

    private final ServerBootstrap serverBootstrap = new ServerBootstrap();

    private final EventLoopGroup boss = new NioEventLoopGroup();

    private final EventLoopGroup work = new NioEventLoopGroup();

    private volatile AtomicBoolean isStart = new AtomicBoolean(false);

    private final DispatcherHandler dispatcherHandler;

    public WebNettyServer(ConfigurableApplicationContext context){
        dispatcherHandler = getDispatcherHandler(context);
    }

    private DispatcherHandler getDispatcherHandler(ConfigurableApplicationContext context){
        DispatcherHandler dispatcherHandler = new DispatcherHandler();
        dispatcherHandler.setApplicationContext(context);
        return dispatcherHandler;
    }

    private volatile int port = 80;

    @Override
    public void start() {
        if(isStart.compareAndSet(false,true)){
            Thread awaitThread = new Thread("server") {
                @Override
                public void run() {
                    String portString = EnvironmentConfig.Server.getPort();
                    if(portString != null && StringUtil.isInt(portString)){
                        port = Integer.valueOf(portString);
                    }
                    serverBootstrap.group(boss, work);
                    serverBootstrap.channel(NioServerSocketChannel.class);
                    serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            final ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(32*1024*1024));
                            pipeline.addLast(new WebServerHandler(dispatcherHandler));
                        }
                    });
                    try {
                        ChannelFuture f = serverBootstrap.bind(port).sync();
                        log.info("服务器启动成功：{}",port);
                        f.channel().closeFuture().sync();
                    } catch (InterruptedException e) {
                        log.error("服务器启动失败："+port,e);
                    } finally {
                        WebNettyServer.this.stop();
                        interrupt();
                    };
                }

            };
            awaitThread.setContextClassLoader(getClass().getClassLoader());
            awaitThread.setDaemon(false);
            awaitThread.start();
        }
    }

    @Override
    public void stop() {
        boss.shutdownGracefully();
        work.shutdownGracefully();
        isStart.set(false);
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
