package com.lts.jvm;

/**
 * @author luotianshun
 * @date 2021/3/5
 * @menu 参考【JVM-GC日志查看分析】 by 爱笨笨的阿狸
 */
public class GcDemo {

    public byte aByte;
    public static void main(String[] args) {
        int _1m = 1024 * 1024;
        byte[] data = new byte[_1m];
        // 将data置为null即让它成为垃圾
        data = null;
        // 通知垃圾回收器回收垃圾
        System.gc();
    }
}
