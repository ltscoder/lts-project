package com.lts.arth.backtraking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/19
 * @menu n皇后问题 lc 51
 */
public class B12NQueen {

    /**
     * 在一个N*N的棋盘上放置N个皇后，每行一个并使其不能互相攻击（同一行、同一列、同一斜线上的皇后都会自动攻击）。
     * 方法1，2，回溯+dfs->递归
     * 方法3回溯+dfs->非递归
     *例如给出n=4，输出
     *
     .Q..
     ...Q
     Q...
     ..Q./n

     ..Q.
     Q...
     ...Q
     .Q..
     * @param args
     */
    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> result = solution(n);
//        print(result);
    }

    private static List<List<Integer>> solution(int n) {
        //方法一，关键在validate判断冲突。
        //结果集，保存符合的path。
        List<List<Integer>> result = new ArrayList<>();
        //保存每一行，皇后的位置列号。如path.get(0) = 1,表示第0行第1列位置应为皇后位。
        List<Integer> path = new ArrayList<>();
        dfsNQueen(0, n, result, path);

        print(result);

        result.clear();
        path.clear();
        //row - j + n -1     row + j

        //方法二，关键也在判断冲突。用三个变量，如下
        //col，依次保存每一列是否已经有皇后了
        boolean[] col = new boolean[n];
        //依次保存主对角线是否有皇后了，~主对角线：捺方向的线，n*n有2*n-1条线，我这里从左下角到右上角序号依次增大。序号=(n-1-row)+col
        boolean[] main = new boolean[2*n-1];
        //依次保存副对角线是否有皇后了，~副对角线：撇方向的线，n*n有2*n-1条线，我这里从左上角到右下角序号依次增大。 序号=row+col
        boolean[] sub = new boolean[2*n-1];
        dfsNQueen2(0, n, col, main, sub, result, path);
        print(result);

        //方法三。使用非递归的回溯法解决n皇后问题
        //参考 【n皇后问题的三种解法】 by IT修道者
        result.clear();
        path.clear();
        dfsWithNoRecursion(n, result);
        print(result);

        //方法四。位运算求解n皇后问题。太难了，没看懂，略。
        return result;


    }


    /**
     * 方法三的校验这个位置如果填入皇后位，是否满足题目要求
     * @param path 每一行存放皇后位置的序号
     * @param row 行号
     */
    private static boolean validate(Integer[] path, int row) {
        int col = path[row];
        for (int i = 0; i < row; i++) {
            int queenCol = path[i];
            if (queenCol == col || Math.abs(row - i) == Math.abs(col - queenCol)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 打印结果
     * @param result
     */
    private static void print(List<List<Integer>> result) {
        for (List<Integer> list : result) {
            System.out.println();
            for (int i = 0; i < list.size(); i++) {
                System.out.println();
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(i) == j) {
                        System.out.print("Q");
                    } else {
                        System.out.print(".");
                    }
                }
            }
        }
    }

    /**
     * dfs获取结果
     * @param row 当前深度遍历的行号
     * @param n 输入n
     * @param result 结果集，保存符合的path。
     * @param path 依次保存每一行，皇后的位置列号。
     */
    private static void dfsNQueen(int row, int n, List<List<Integer>> result, List<Integer> path) {
        if (row >= n) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (validate(path, row, col, n)) {
                path.add(col);
                dfsNQueen(row + 1, n, result, path);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 校验这个位置如果填入皇后位，是否满足题目要求
     * @param path 依次保存每一行，皇后的位置列号。
     * @param row 校验位置的行号
     * @param col 校验位置的列号
     * @param n 输入n
     * @return true可以填入皇后，false不能填入皇后
     */
    private static boolean validate(List<Integer> path, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            //获取这一行填入皇后的列号。
            int queenCol = path.get(i);
            //如果列号相等，或者  行号的差绝对值==列号的差的绝对值，说明冲突了
            if (queenCol == col || Math.abs(i - row) == Math.abs(queenCol - col)) {
                return false;
            }
        }
        return true;
    }

//********************************************************************************************************
    /**
     * 方法二dfs获取结果
     * @param row 深度遍历的行号
     * @param n 输入n
     * @param col 依次保存每一列是否已经有皇后了
     * @param main 依次保存主对角线是否有皇后了
     * @param sub 依次保存副对角线是否有皇后了
     * @param result 结果集，保存符合的path。
     * @param path 依次保存每一行，皇后的位置列号。
     */
    private static void dfsNQueen2(int row, int n, boolean[] col, boolean[] main, boolean[] sub, List<List<Integer>> result, List<Integer> path) {
        if (row >= n) {
            result.add(new ArrayList<>(path));
            return;
        }

        //遍历每一列
        for (int i = 0; i < n; i++) {
            //判断列，主对角线，副对角线是否有皇后。
            if (!col[i] && !main[n - 1 - row + i] && !sub[row + i]) {
                col[i] = true;
                main[n - 1 - row + i] = true;
                sub[row + i] = true;

                path.add(i);
                dfsNQueen2(row + 1, n, col, main, sub, result, path);
                col[i] = false;
                main[n - 1 - row + i] = false;
                sub[row + i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

//********************************************************************************************************

    /**
     * 方法三dfs不用递归
     * @param n 输入n
     * @param result 结果
     */
    private static void dfsWithNoRecursion(int n, List<List<Integer>> result) {

        int row=0;
//        List<Integer> path = new ArrayList<>();
//        path.add(row, 0);

        //存放每一行放置皇后的位置列号
        Integer[] path = new Integer[n];
        path[row] = 0;  //从第0行第0列开始
        while (row >= 0) {

            //判断当前位置的row和col是否满足要求
            if (row < n && path[row] < n) {
                if (validate(path, row)) {
                    //校验通过，可以放置皇后，~则dfs下一行。
                    row++;
                    if (row < path.length) {  //判断越界
                        path[row] = 0;  //每一行从第0列开始
                    }
                } else {
                    //校验不通过，则继续当前行的下一列。
                    path[row]++;
                }
            } else {
                if (row >= n) {
                    //dfs行遍历完了，加入结果
                    result.add(new ArrayList(Arrays.asList(path)));
                }
                //***************~重点：回溯，从上一行皇后位置的下一列继续探测****************************************
                //如果k>num会执行下面两行代码，因为虽然找到了N皇后问题的一个解，但是要找的是所有解，需要回溯，从当前放置皇后的下一列继续探测
                //如果a[k]>num也会执行下面两行代码，就是说在当前行没有找到可以放置皇后的位置，于是回溯，从上一行皇后位置的下一列继续探测
                row--;
                if (row >=0) {  //判断越界
                    path[row]++;
                }
            }
        }
    }
}
