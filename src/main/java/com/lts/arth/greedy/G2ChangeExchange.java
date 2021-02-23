package com.lts.arth.greedy;

import java.util.Arrays;

/**
 * @author luotianshun
 * @date 2021/2/20
 * @menu 零钱兑换 lc 322
 */
public class G2ChangeExchange {

    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 方法一：回溯+dfs，贪心（~贪心牵强）
     * 方法二：动态规划
     * @param args
     */
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;

        int result = solution(coins, amount);
    }

    private static int solution(int[] coins, int amount) {

        //法一，回溯+dfs，贪心
        //排序
        Arrays.sort(coins);
        int result = dfsChangeExchange(coins, amount, 0);
        System.out.println(result);
        
        //法二，动态规划
        result = dynamicChangeExchange(coins, amount);
        System.out.println(result);
        return result;
    }

    /**
     * 动态规划
     * @param coins 输入硬币金额
     * @param amount 输入凑的金额
     * @return
     */
    private static int dynamicChangeExchange(int[] coins, int amount) {
        //dp table，初始化值都为dp[i] = amount + 1。 dp[0] = 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        //状态转移方程：
        //dp(n)=min{dp(n-coin) + 1 | coin in coins},n >0
        //     =0,n=0
        //     =-1,n<0
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                //i - coin < 0时，说明凑不齐amount，跳过。
                if (i - coin >= 0) {
                    int pre = dp[i - coin];
                    dp[i] = Math.min(dp[i], pre + 1);
                }
            }
        }
        //dp[amount] > amount，如=amount+2，amount+3，说明无法凑齐amount。返回-1
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * dfs求解。written by myself
     * @param coins 已排序的钱币
     * @param amountLeft 还需要凑多少钱
     * @param num 硬币数量
     * @return
     */
    private static int dfsChangeExchange(int[] coins, int amountLeft, int num) {
        //刚好凑齐了，返回硬币数量
        if (amountLeft == 0) {
            return num;
        }

        //遍历每种面额的硬币，从最大的开始，~贪心
        for (int i = coins.length - 1; i >= 0; i--) {
            //剪枝，拼凑的钱比需要的多。~也是深度的临界条件，不再继续深度了。
            if (amountLeft - coins[i] < 0) {
                continue;
            }
            int num2 = dfsChangeExchange(coins, amountLeft - coins[i], num + 1);
            //num2 != -1的话，即求得最优解了，直接返回
            if (num2 != -1) {
                return num2;
            }
        }
        //没有满足的，返回-1
        return -1;
    }
}
