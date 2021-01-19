package com.lts;

import java.io.IOException;

/**
 * @author luotianshun
 * @date 2020/12/23
 * @menu
 */
public class ApplicationTest {
    public static void main(String[] args) throws IOException {
//        System.out.println(new Date().getTime());
//        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).toInstant(ZoneOffset.of("+8")).toEpochMilli());

//        ConcurrentReferenceHashMap map = new ConcurrentReferenceHashMap(16, ConcurrentReferenceHashMap.ReferenceType.WEAK);
//        map.put("key1","val");
//        Object key1 = map.get("key1");
//        System.out.println(key1);
//
//        System.out.println(map);
//
//        System.gc();
//        try {
//            Thread.currentThread().sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(map);

          //map value remove
//        Map<Integer, String> map = new HashMap<Integer, String>();
//        map.put(1, "a");
//        map.put(2, "b");
//        map.put(3, "c");
//        map.put(4, "d");
//        map.put(5, "e");
//        map.put(6, "f");
//        System.out.println(map);
//        Collection<String> col = map.values();
//        while(true == col.contains("c")) {
//            col.remove("c");
//        }
//        System.out.println(map);



        // 监听指定的端口
//        int port = 55533;
//        ServerSocket server = new ServerSocket(port);
//        // server将一直等待连接的到来
//        System.out.println("server将一直等待连接的到来");
//
//        //如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
//        ExecutorService threadPool = Executors.newFixedThreadPool(100);
//
//        while (true) {
//            Socket socket = server.accept();
//
//            Runnable runnable=()->{
//                try {
//                    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
//                    InputStream inputStream = socket.getInputStream();
//                    byte[] bytes = new byte[1024];
//                    int len;
//                    StringBuilder sb = new StringBuilder();
//                    while ((len = inputStream.read(bytes)) != -1) {
//                        // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//                        sb.append(new String(bytes, 0, len, "UTF-8"));
//                    }
//                    System.out.println("get message from client: " + sb);
//                    inputStream.close();
//                    socket.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            };
//            threadPool.submit(runnable);
//        }


    }
}
