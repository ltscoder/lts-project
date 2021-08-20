package com.lts.netty.clientmulticonnect;

/**
 * @author luotianshun
 * @date 2021/1/15
 * @menu
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiEchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static volatile AtomicInteger id = new AtomicInteger(0);
    private int thisId;
    private int sendTime = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //方式1：
        System.out.println("client channelActive..");
        thisId = id.getAndIncrement();
        if (thisId % 1000 == 0) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!clientId=" + thisId + ",send Time=" + ++sendTime, CharsetUtil.UTF_8)); // 必须有flush
        }

        // 方式2：必须存在flush
        // ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        // ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("client channelRead..");
        Thread.sleep(1000);
        ByteBuf buf = msg.readBytes(msg.readableBytes());
//        System.out.println("Client received:" + ByteBufUtil.hexDump(buf) + "; The value is:" + buf.toString(Charset.forName("utf-8")));
        System.out.println("thisId=" + thisId + "received:" + buf.toString(Charset.forName("utf-8")));
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!clientId=" + thisId + ",send Time=" + ++sendTime, CharsetUtil.UTF_8));
        //ctx.channel().close().sync();// client关闭channel连接
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
