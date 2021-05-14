package com.lts.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * @author luotianshun
 * @date 2021/4/19
 * @menu cas用lock前缀已经保证了原子性可见性，为什么value也用volatile修饰。
 *    参考中一回答者：set和get方法需要用volatile来保证。
 *
 * 参考【AtomicInteger 中的value为啥要定义为volatile】 by duxd2017
 */
public class VolatileTest {

    // 线程数
    private static final int THREADS = 100;
    private static final CountDownLatch latch = new CountDownLatch(THREADS);
    private static final NonVolatileValueAtomicInteger ato = new NonVolatileValueAtomicInteger(0);
    static int a = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < THREADS; i++) {
            new Thread(new Worker(ato)).start();
        }

        latch.await();
        System.out.printf("value最终值:[%d]", ato.get());//  10000
        System.out.printf("int最终值:[%d]", a); //<1000

    }

    static class Worker implements Runnable {

        private final NonVolatileValueAtomicInteger ato;

        Worker(NonVolatileValueAtomicInteger ato) {
            this.ato = ato;
        }

        @Override
        public void run() {
            for (int i = 0; i < THREADS; i++) {
                ato.getAndIncrement();
                a++;
            }
            latch.countDown();
        }
    }



    /**
     * copy AtomicInteger，只是value属性去掉volatile。
     */
    public static class NonVolatileValueAtomicInteger extends Number implements java.io.Serializable {

        // setup to use Unsafe.compareAndSwapInt for updates
        private static final Unsafe unsafe;
        private static final long valueOffset;

        static {
            try {
                Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
                constructor.setAccessible(true);
                unsafe = constructor.newInstance();
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e2) {
                throw new Error(e2);
            }
            try {
                valueOffset = unsafe.objectFieldOffset
                        (NonVolatileValueAtomicInteger.class.getDeclaredField("value"));
            } catch (Exception ex) { throw new Error(ex); }
        }

        private int value;

        /**
         * Creates a new NonVolatileValueAtomicInteger with the given initial value.
         *
         * @param initialValue the initial value
         */
        public NonVolatileValueAtomicInteger(int initialValue) {
            value = initialValue;
        }

        /**
         * Creates a new NonVolatileValueAtomicInteger with initial value {@code 0}.
         */
        public NonVolatileValueAtomicInteger() {
        }

        /**
         * Gets the current value.
         *
         * @return the current value
         */
        public final int get() {
            return value;
        }

        /**
         * Sets to the given value.
         *
         * @param newValue the new value
         */
        public final void set(int newValue) {
            value = newValue;
        }

        /**
         * Eventually sets to the given value.
         *
         * @param newValue the new value
         * @since 1.6
         */
        public final void lazySet(int newValue) {
            unsafe.putOrderedInt(this, valueOffset, newValue);
        }

        /**
         * Atomically sets to the given value and returns the old value.
         *
         * @param newValue the new value
         * @return the previous value
         */
        public final int getAndSet(int newValue) {
            return unsafe.getAndSetInt(this, valueOffset, newValue);
        }

        /**
         * Atomically sets the value to the given updated value
         * if the current value {@code ==} the expected value.
         *
         * @param expect the expected value
         * @param update the new value
         * @return {@code true} if successful. False return indicates that
         * the actual value was not equal to the expected value.
         */
        public final boolean compareAndSet(int expect, int update) {
            return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
        }

        /**
         * Atomically sets the value to the given updated value
         * if the current value {@code ==} the expected value.
         *
         * <p><a href="package-summary.html#weakCompareAndSet">May fail
         * spuriously and does not provide ordering guarantees</a>, so is
         * only rarely an appropriate alternative to {@code compareAndSet}.
         *
         * @param expect the expected value
         * @param update the new value
         * @return {@code true} if successful
         */
        public final boolean weakCompareAndSet(int expect, int update) {
            return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
        }

        /**
         * Atomically increments by one the current value.
         *
         * @return the previous value
         */
        public final int getAndIncrement() {
            return unsafe.getAndAddInt(this, valueOffset, 1);
        }

        /**
         * Atomically decrements by one the current value.
         *
         * @return the previous value
         */
        public final int getAndDecrement() {
            return unsafe.getAndAddInt(this, valueOffset, -1);
        }

        /**
         * Atomically adds the given value to the current value.
         *
         * @param delta the value to add
         * @return the previous value
         */
        public final int getAndAdd(int delta) {
            return unsafe.getAndAddInt(this, valueOffset, delta);
        }

        /**
         * Atomically increments by one the current value.
         *
         * @return the updated value
         */
        public final int incrementAndGet() {
            return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
        }

        /**
         * Atomically decrements by one the current value.
         *
         * @return the updated value
         */
        public final int decrementAndGet() {
            return unsafe.getAndAddInt(this, valueOffset, -1) - 1;
        }

        /**
         * Atomically adds the given value to the current value.
         *
         * @param delta the value to add
         * @return the updated value
         */
        public final int addAndGet(int delta) {
            return unsafe.getAndAddInt(this, valueOffset, delta) + delta;
        }

        /**
         * Atomically updates the current value with the results of
         * applying the given function, returning the previous value. The
         * function should be side-effect-free, since it may be re-applied
         * when attempted updates fail due to contention among threads.
         *
         * @param updateFunction a side-effect-free function
         * @return the previous value
         * @since 1.8
         */
        public final int getAndUpdate(IntUnaryOperator updateFunction) {
            int prev, next;
            do {
                prev = get();
                next = updateFunction.applyAsInt(prev);
            } while (!compareAndSet(prev, next));
            return prev;
        }

        /**
         * Atomically updates the current value with the results of
         * applying the given function, returning the updated value. The
         * function should be side-effect-free, since it may be re-applied
         * when attempted updates fail due to contention among threads.
         *
         * @param updateFunction a side-effect-free function
         * @return the updated value
         * @since 1.8
         */
        public final int updateAndGet(IntUnaryOperator updateFunction) {
            int prev, next;
            do {
                prev = get();
                next = updateFunction.applyAsInt(prev);
            } while (!compareAndSet(prev, next));
            return next;
        }

        /**
         * Atomically updates the current value with the results of
         * applying the given function to the current and given values,
         * returning the previous value. The function should be
         * side-effect-free, since it may be re-applied when attempted
         * updates fail due to contention among threads.  The function
         * is applied with the current value as its first argument,
         * and the given update as the second argument.
         *
         * @param x the update value
         * @param accumulatorFunction a side-effect-free function of two arguments
         * @return the previous value
         * @since 1.8
         */
        public final int getAndAccumulate(int x,
                                          IntBinaryOperator accumulatorFunction) {
            int prev, next;
            do {
                prev = get();
                next = accumulatorFunction.applyAsInt(prev, x);
            } while (!compareAndSet(prev, next));
            return prev;
        }

        /**
         * Atomically updates the current value with the results of
         * applying the given function to the current and given values,
         * returning the updated value. The function should be
         * side-effect-free, since it may be re-applied when attempted
         * updates fail due to contention among threads.  The function
         * is applied with the current value as its first argument,
         * and the given update as the second argument.
         *
         * @param x the update value
         * @param accumulatorFunction a side-effect-free function of two arguments
         * @return the updated value
         * @since 1.8
         */
        public final int accumulateAndGet(int x,
                                          IntBinaryOperator accumulatorFunction) {
            int prev, next;
            do {
                prev = get();
                next = accumulatorFunction.applyAsInt(prev, x);
            } while (!compareAndSet(prev, next));
            return next;
        }

        /**
         * Returns the String representation of the current value.
         * @return the String representation of the current value
         */
        public String toString() {
            return Integer.toString(get());
        }

        /**
         * Returns the value of this {@code NonVolatileValueAtomicInteger} as an {@code int}.
         */
        public int intValue() {
            return get();
        }

        /**
         * Returns the value of this {@code NonVolatileValueAtomicInteger} as a {@code long}
         * after a widening primitive conversion.
         * @jls 5.1.2 Widening Primitive Conversions
         */
        public long longValue() {
            return (long)get();
        }

        /**
         * Returns the value of this {@code NonVolatileValueAtomicInteger} as a {@code float}
         * after a widening primitive conversion.
         * @jls 5.1.2 Widening Primitive Conversions
         */
        public float floatValue() {
            return (float)get();
        }

        /**
         * Returns the value of this {@code NonVolatileValueAtomicInteger} as a {@code double}
         * after a widening primitive conversion.
         * @jls 5.1.2 Widening Primitive Conversions
         */
        public double doubleValue() {
            return (double)get();
        }
    }
}
