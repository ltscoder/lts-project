package com.lts.arth.dynamic;

import java.util.Arrays;

/**
 * @author luotianshun
 * @date 2021/2/22
 * @menu 最小路径和 lc 64
 */
public class D3MinPathSum {

    /**
     * 现在给你输入一个二维数组grid，其中的元素都是非负整数，现在你站在左上角，只能向右或者向下移动，需要到达右下角。
     * 现在请你计算，经过的路径和最小是多少
     * 分治/动态规划
     * 分析状态转移方程为：dp[i][j] = min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j]
     * @param args
     */
    public static void main(String[] args) {

        int[][] input = {{1,3,1}, {1,5,1},{4,2,1}};
        int result = solution(input);

    }

    private static int solution(int[][] input) {

        //方法1，分治
        int sum = divideAndConquerMinPathSum(input, input.length - 1, input[0].length - 1);
        System.out.println(sum);

        //方法1.1分治优化，带备忘录
        //从dp(i, j)递归到dp(i-1, j-1)，有几种不同的递归调用路径？
        //可以是dp(i, j) -> #1 -> #2或者dp(i, j) -> #2 -> #1，不止一种，说明dp(i-1, j-1)会被多次计算，所以一定存在重叠子问题。
        int[][] memo = new int[input.length][input[0].length];
        // 构造备忘录，初始值全部设为 -1
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        sum = divideAndConquerWithMomoMinPathSum(input, memo, input.length - 1, input[0].length - 1);
        System.out.println(sum);

        //方法二，动态规划
        sum = dynamicMinPathSum(input);
        System.out.println(sum);


        return 0;
    }

    /**
     * 动态规划
     * @param grid 输入grid
     * @return
     */
    private static int dynamicMinPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        //base case
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        //状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(
                        dp[i - 1][j],
                        dp[i][j - 1]
                ) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     *分治优化，带备忘录
     * @param grid 输入grid
     * @param memo 备忘录
     * @param i 行坐标
     * @param j 列坐标
     * @return
     */
    private static int divideAndConquerWithMomoMinPathSum(int[][] grid, int[][] memo, int i, int j) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        //避免重复计算
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        //将结果计入备忘录
        memo[i][j] = Math.min(
                divideAndConquerWithMomoMinPathSum(grid, memo, i -1, j),
                divideAndConquerWithMomoMinPathSum(grid, memo, i, j - 1))
                + grid[i][j];

        return memo[i][j];
    }

    /**
     * 分治
     * @param grid 输入grid
     * @param i 行坐标
     * @param j 列坐标
     * @return
     */
    private static int divideAndConquerMinPathSum(int[][] grid, int i, int j) {
        //base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        // 如果索引出界，返回一个很大的值，
        // 保证在取 min 的时候不会被取到
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        // 左边和上面的最小路径和加上 grid[i][j]
        // 就是到达 (i, j) 的最小路径和
        return Math.min(
                divideAndConquerMinPathSum(grid, i - 1, j),
                divideAndConquerMinPathSum(grid, i, j - 1))
                + grid[i][j];
    }
}
