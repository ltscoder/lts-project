package com.lts.arth;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author luotianshun
 * @date 2021/2/3
 * @menu
 */
public class NumIsland {

    /**
     * 牛客/力扣，岛屿数量问题：给一个二维由0，1组成的数组，上下左右挨着的1是组成一个岛屿，求岛屿数量
     * 参考 【LeetCode 200：岛屿数量 Number of Islands】 by 爱写Bug
     * 用到的算法思想：搜索算法：dfs/bfs
     * @param args
     */
    public static void main(String args[]) {

        int[][] arr = {{1,1,0,0,0},{1,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}};

        //方法1：dfs深度搜索。~遍历数组，遇到1则count++，然后对这个点的四周做深度搜索，并标记为0
        int num = dfsSolution(arr);
        System.out.println(num);

        //方法2：bfs广度搜索，~遍历数组，遇到1则count++，然后对这个点的四周做广度搜索，并标记为0
        int num2 = bfsSolution(arr);
        System.out.println(num2);
        //博客上给的：
        //DFS（深度优先搜索）：从一个为1的根节点开始访问，从每个相邻1节点向下访问到顶点（周围全是水），依次访问其他相邻1节点到顶点
        //BFS（广度优先搜索）：从一个为1的根节点开始访问，每次向下访问一个节点，直到访问到最后一个顶点
        
    }


    private static int dfsSolution(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length, columns = grid[0].length, count = 0;
        for (int i = 0; i < row; i++) {//遍历所有点
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, row, columns);//dfs遍历所有连接的点
                    count++;//记录岛屿数量
                }
            }
        }
        return count;
    }

    private static void dfs(int[][] grid, int i, int j, int row, int columns) {
        if (i < 0 || j < 0 || i >= row || j >= columns || grid[i][j] == 0) return;//基线条件
        grid[i][j] = 0;//遍历过的点置 0
        dfs(grid, i + 1, j, row, columns);
        dfs(grid, i, j + 1, row, columns);
        dfs(grid, i - 1, j, row, columns);
        dfs(grid, i, j - 1, row, columns);
    }



    private static int bfsSolution(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length, columns = grid[0].length, count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {//遍历所有节点
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, row, columns);
                    count++;//记录岛屿数量
                }
            }
        }
        return count;
    }

    private static void bfs(int[][] grid, int i, int j, int row, int columns) {
        Queue<Integer> loc = new LinkedList<>();//队列暂存值为 1 的点
        loc.add(i * columns + j);//暂存该点位置，也可以用一个[i,j]数组表示，不过占用空间也会大一倍
        while (!loc.isEmpty()) {
            int id = loc.remove();//取出位置
            int r = id / columns, c = id % columns;//分解位置得到索引
            if (r - 1 >= 0 && grid[r - 1][c] == 1) { //如果这个位置为1，
                loc.add((r - 1) * columns + c); //加入队列
                grid[r - 1][c] = 0; //标记为0
            }
            if (r + 1 < row && grid[r + 1][c] == 1) {
                loc.add((r + 1) * columns + c);
                grid[r + 1][c] = 0;
            }
            if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                loc.add(r * columns + c - 1);
                grid[r][c - 1] = 0;
            }
            if (c + 1 < columns && grid[r][c + 1] == 1) {
                loc.add(r * columns + c + 1);
                grid[r][c + 1] = 0;
            }
        }
    }
}
