package com.lts.netty.clientmulticonnect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * test 参考【netty 客户端创建多个连接】by lesliefang
 * 类似server端，一个客户端EventLoopGroup创建多个连接到服务端。
 * 连接的服务端的ip端口，可以是同一个，也可以是不同的。
 * 客户端每一个连接会默认分配占用一个端口。
 *
 * @author luotianshun
 * @date 2021/8/20
 * @menu
 */
public class A1MultiConnectClient {
    private Bootstrap b = new Bootstrap();
    private EventLoopGroup group;
    private static AtomicInteger failedCount = new AtomicInteger(0);

    private static AtomicInteger successCount = new AtomicInteger(0);

    public A1MultiConnectClient() {
        group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        ch.pipeline().addLast(new MultiEchoClientHandler());
                    }
                });
    }

    public void connect(String host, int port) {
        System.out.println("11111111111 connect " + host + " " + Thread.currentThread());
        b.connect(host, port).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                /*
                 * 这里就不是主线程了，这里是 netty 线程中执行
                 */
                if (future.isSuccess()) {
                    System.out.println("2222222222222 connect success " + host + " " + Thread.currentThread() + "success count:" + successCount.incrementAndGet());
                } else {
                    System.out.println("333333333333333 connect failed " + host + " " + Thread.currentThread() + "failed count:" + failedCount.incrementAndGet());
                    // 连接不成功，5秒后重新连接
//                    future.channel().eventLoop().schedule(new Runnable() {
//                        @Override
//                        public void run() {
//                            System.out.println("4444444444444  reconnect " + host + " " + Thread.currentThread());
//                            connect(host, port);
//                        }
//                    }, 5, TimeUnit.SECONDS);
                }
            }
        });
    }

    public void stop() {
        if (group != null) {
            group.shutdownGracefully();
            group = null;
        }
    }

    public static void main(String[] args) {
//        String[] ips = new String[]{
//                "192.168.1.100",
//                "192.168.1.101",
//                "192.168.1.102",
//        };

        A1MultiConnectClient nettyClient = new A1MultiConnectClient();

        for (int i = 0; i < 100000; i++) {
            nettyClient.connect("127.0.0.1", 65535);
        }

        // 这里主线程提前退出了，但JVM进程没有退出
        System.out.println("main thread finish");
    }
}
