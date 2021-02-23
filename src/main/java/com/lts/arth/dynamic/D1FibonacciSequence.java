package com.lts.arth.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/20
 * @menu 斐波那契数列 lc 509
 */
public class D1FibonacciSequence {

    /**
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给你 n ，请计算 F(n) 。
     *
     * 分治/动态规划
     * @param args
     */
    public static void main(String[] args) {

        int n = 6;
        int result = solution(n);
    }

    private static int solution(int n) {
        //方法1.分治->递归.时间O(2^n)，空间O(logn)（~空间不是o(n)吗，方法栈深度最深为n）
        int result = DCFibonacciSequence(n);
        System.out.println(result);
        //方法2，分治->递归优化，带备忘录
        ArrayList<Integer> memo = new ArrayList<>();
        memo.add(0);
        memo.add(1);
        result = DCWithMemoFibonacciSequence(n, memo);
        System.out.println(result);
        //方法3，动态规划 时间O(n),空间O(n)
        result = dynamicFibonacciSequence(n);
        System.out.println(result);
        //方法4，动态规划优化，当前状态只和之前的两个状态有关，其实并不需要那么长的一个 DP table 来存储所有的状态，只要想办法存储之前的两个状态就行了
        result = dynamicWithoutDpFibonacciSequence(n);
        System.out.println(result);
        return 0;
    }

    /**
     * 动态规划优化,不用dp table，只用两个变量缓存n-1，n-2的值，
     * @param n
     * @return
     */
    private static int dynamicWithoutDpFibonacciSequence(int n) {
        if (n <= 1) {
            return n;
        }

        int slow = 0;
        int fast = 1;
        for (int i = 2; i <= n; i++) {
            int temp = fast;
            fast = slow + fast;
            slow = temp;
        }
        return fast;
    }

    /**
     * 动态规划
     * @param n 输入n
     * @return
     */
    private static int dynamicFibonacciSequence(int n) {
        if (n <= 1) {
            return n;
        }

        //dynamic programing table
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        dp.add(1);
        for (int i = 2; i <= n; i++) {
            dp.add(dp.get(i - 1) + dp.get(i - 2));
        }
        return dp.get(n);
    }

    /**
     * 分治(Divide and Conquer)->递归优化，带备忘录
     * @param n 输入n
     * @param memo 备忘录（缓存）
     * @return
     */
    private static int DCWithMemoFibonacciSequence(int n, List<Integer> memo) {
        //n==0,1的情况,已经缓存了，略

        //如果还没缓存，则计算
        if (n >= memo.size()) {
            //计算，然后放缓存
            int ret = DCWithMemoFibonacciSequence(n - 1, memo) + DCWithMemoFibonacciSequence(n - 2, memo);
            memo.add(n, ret);
        }
        //从缓存中取，并返回。
        return memo.get(n);
    }

    /**
     * 分治(Divide and Conquer)->递归
     * @param n 输入n
     * @return
     */
    private static int DCFibonacciSequence(int n) {
        if (n <= 1) {
            return n;
        }
        return DCFibonacciSequence(n - 1) + DCFibonacciSequence(n - 2);
    }
}
