package com.lts.nio;

import java.io.IOException;

/**
 * 代码来源 【NIO源码阅读】 by 超大的皮卡丘
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //采用默认值
            }
        }
        //多路复用类，是一个独立的线程，负责轮训多路复用器Selctor,处理多个客户端的并发接入。
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}

