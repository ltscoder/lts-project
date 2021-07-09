package com.lts.juc;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luotianshun
 * @date 2021/7/8
 * @menu
 */

public class SynchronizedLockTest {
    private int defaultThreadCount = 5;
    private int defaultEndCount = 10000;
    private int defaultSleepTime = 2000;

    public int count = 0;
    private int endCount = 5000000;
    private int threadCount = 100;
    private long startTime;
    private long sleepTime = 30000;
    private Lock fairLock = new ReentrantLock(true);

    private Lock unFairLock = new ReentrantLock();


    public static void main(String[] args) {

    }

    /**
     * ~根据testFairLock，testUnFairLock， testSynchronized测试结果：
     * 1lock 公平锁比非公平锁性能差，好像是因为非公平锁获取锁时不必排队(参考ReentrantLock)，可以直接尝试获取，获取不到再排队阻塞，减少了上下文切换的消耗。
     * 2Synchronized这里性能和lock非公平锁性能差不多，测试时用500线程共享计数到500万，应该算竞争激烈了。结果耗时lock130毫秒左右，synchronized180毫秒左右。
     *   每次计数时System.out.println(Thread.currentThread().getName())，发现非公平锁lock和synchronized都是连续一个线程技术多次，说明减少了上下文切换的次数。
     *   估计经过jdk的优化synchronized重量级锁和lock非公平锁原理都差不多了。性能接近。
     * @throws InterruptedException
     */

    @Test
    public void testFairLock() throws InterruptedException {

        startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[threadCount];
        for (int i = 0;i < threadCount;i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int a = 0;
                    while (count < endCount) {
                        try {

                            fairLock.lock();
                            if (count >= endCount) {
                                continue;
                            }
//                            System.out.println(Thread.currentThread().getName());
                            count++;
                            a++;
                        } catch (Exception e) {
                        } finally {
                            fairLock.unlock();
                        }
                    }
                    System.out.println("fairLockCost+" + (System.currentTimeMillis() - startTime) + " cur thread add num:" + a);
                    System.out.println(count);
                }
            }, "thread" + i);
            thread.start();
            threads[i] = thread;
        }
        //等待线程跑完，console输出。
        for (Thread thread : threads) {
            thread.join();
        }
//        Thread.sleep(sleepTime);
    }

    @Test
    public void testUnFairLock() throws InterruptedException {

        startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[threadCount];
        for (int i = 0;i < threadCount;i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int a = 0;
                    while (count < endCount) {
                        try {
                            unFairLock.lock();
                            if (count >= endCount) {
                                continue;
                            }
//                            System.out.println(Thread.currentThread().getName());

                            count++;
                            a++;
                        } finally {
                            unFairLock.unlock();
                        }
                    }
                    System.out.println("unFairLockCost+" + (System.currentTimeMillis() - startTime) + " cur thread add num:" + a);
                    System.out.println(count);
                }
            }, "thread" + i);
            thread.start();
            threads[i] = thread;
        }
        //等待线程跑完，console输出。
        for (Thread thread : threads) {
            thread.join();
        }
//        Thread.sleep(sleepTime);
    }

    @Test
    public void testSynchronized() throws InterruptedException {

        startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[threadCount];
        for (int i = 0;i < threadCount;i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int a = 0;
                    while (count < endCount) {
                        synchronized (SynchronizedLockTest.class) {
                            if (count >= endCount) {
                                continue;
                            }

                            //endCount比较大时，不要sys.out输出
//                            System.out.println(Thread.currentThread().getName());
                            count++;
                            a++;
                        }
                    }
                    System.out.println("SynchronizedCost+" + (System.currentTimeMillis() - startTime) + " cur thread add num:" + a);
                    System.out.println(count);
                }
            }, "thread" + i);
            thread.start();
            threads[i] = thread;
        }
        //等待线程跑完，console输出。
        for (Thread thread : threads) {
            thread.join();
        }
//        Thread.sleep(sleepTime);
    }
}
