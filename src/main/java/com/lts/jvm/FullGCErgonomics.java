package com.lts.jvm;

/**
 * @author luotianshun
 * @date 2021/3/8
 * @menu 参考 【FullGC触发条件和解读GC日志】 by xszhaobo
 * -Xms200m  -Xmx200m -Xmn32m -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+PrintGCDetails -XX:GCTimeRatio=999999999 -XX:+PrintGCTimeStamps -XX:AdaptiveSizePolicyOutputInterval=1 -XX:GCTimeRatio=1 -XX:+PrintGCApplicationStoppedTime
 */
public class FullGCErgonomics {

    public static void main(String[] args) throws InterruptedException {
        // 设置大对象，新生代内存有32m，不够
//        byte[] bigObj1 = new byte[1024 * 1024 * 160];
//        byte[] bigObj2 = new byte[1024 * 1024 * 70];

//        byte[] bigObj1 = new byte[1024 * 1024 * 160];
//        bigObj1= null;
//        bigObj1 = new byte[1024 * 1024 * 160];
//        bigObj1= null;
//        bigObj1 = new byte[1024 * 1024 * 160];
//        bigObj1= null;
//        bigObj1 = new byte[1024 * 1024 * 160];


        //test2
        //1：创建一个对象，当发现eden区空间不够时，放到old区。
        //2：在1的基础上，发现old区空间也不够时，触发young gc。而不是full gc
//        byte[] bigObj1 = new byte[1024 * 1024 * 17];
//        bigObj1 = new byte[1024 * 1024 * 17];
//        bigObj1 = new byte[1024 * 1024 * 17];
//        bigObj1 = new byte[1024 * 1024 * 17];
//        bigObj1 = new byte[1024 * 1024 * 17];
//        bigObj1 = new byte[1024 * 1024 * 17];
//        bigObj1 = new byte[1024 * 1024 * 17];
//        bigObj1 = new byte[1024 * 1024 * 17];
//        bigObj1 = new byte[1024 * 1024 * 17];
//        bigObj1 = new byte[1024 * 1024 * 17];
//        //再new 1个对象，old区也放不下了，young gc
//        bigObj1 = new byte[1024 * 1024 * 17];


        //test3   Full GC (Ergonomics)   a=150就会出现Full GC (Ergonomics)，而a=140就不会出现，什么原因?
//        int a = 150;
//        byte[] bigObj1 = new byte[1024 * 1024 * a];
//        bigObj1 = new byte[1024 * 1024 * a];
//        bigObj1 = new byte[1024 * 1024 * a];


        //test4
        byte[] bytes;
        for (int i = 0; i < 1024 * 1024; i++) {
            bytes = new byte[150];
        }


    }
}

//运行后
//[GC (Allocation Failure) [PSYoungGen: 5424K->1472K(28672K)] 169264K->165320K(200704K), 0.0011043 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
//[Full GC (Ergonomics) [PSYoungGen: 1472K->0K(28672K)] [ParOldGen: 163848K->165159K(172032K)] 165320K->165159K(200704K), [Metaspace: 3339K->3339K(1056768K)], 0.0063087 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
//[GC (Allocation Failure) [PSYoungGen: 0K->0K(28672K)] 165159K->165159K(200704K), 0.0011707 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
//[Full GC (Allocation Failure) [PSYoungGen: 0K->0K(28672K)] [ParOldGen: 165159K->165140K(172032K)] 165159K->165140K(200704K), [Metaspace: 3339K->3339K(1056768K)], 0.0070627 secs] [Times: user=0.16 sys=0.00, real=0.01 secs]

//~解析:
//1创建bigObj1，年轻代放不下，直接放到老年代。
//2创建bigObj2，

