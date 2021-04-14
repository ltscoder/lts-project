package com.lts.jvm;

/**
 * @author luotianshun
 * @date 2021/3/8
 * @menu 参考 【G1日志详解】 by Deegue
 * -Xms200m -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintAdaptiveSizePolicy
 */
//-XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions -XX:+G1TraceEagerReclaimHumongousObjects
public class G1Test {

    public static void main(String[] args) throws InterruptedException {

        //test1    -Xms200m -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintAdaptiveSizePolicy
        byte[][] bytess = new byte[1024 * 1024][];
        byte[] bytes;
        for (int i = 0; i < 1024 * 1024; i++) {
            bytes = new byte[150];
            bytess[i]= bytes;
        }
        bytess = null;

    bytess = new byte[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            bytes = new byte[150];
            bytess[i]= bytes;
        }
    }
}


