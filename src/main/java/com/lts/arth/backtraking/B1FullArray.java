package com.lts.arth.backtraking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/6
 * @menu 全排列 leecode 46
 */
public class B1FullArray {

    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列
     * 参考【46. 全排列】 by csdnyq
     * 思想 1回溯法+dfs  2解法2交换元素，略
     * @param args
     */
    public static void main(String[] args) {
        int[] input = {2,1,3};

        //回溯法
        List<List<Integer>> solution = solution(input);
        System.out.println(solution);

        //元素交换法，略
    }

    public static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> res = new LinkedList<>(); //结果变量

        if (nums == null || nums.length == 0) {
            return res;
        }

        boolean[] used = new boolean[nums.length]; //表示nums中的每位数字是否已经使用
        Arrays.fill(used, false); //全部数字都没有使用
        List<Integer> path = new LinkedList<>(); //每一种组合

        dfsFullArray(nums, res, path, used);

        return res;
    }

    /**
     * 生成全排列
     *~考虑按树从顶层向下，每一层确定下一个位置的数，dfs+回溯
     * @param nums 全排列的数字
     * @param res  结果变量
     * @param path  每一种组合
     * @param used 对应nums中的每位数字是否已经使用
     */
    private static void dfsFullArray(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] used) {
//        //nums.length == path.size(),说明nums被用完了，加入结果
//        if (nums.length == path.size()) {
//            res.add(new LinkedList(path));
//            return;
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            // 如果这一位数字已经使用了
//            if (used[i] == true) {
//                continue;
//            }
//
//            used[i] = true; //标明这个数字已经使用了
//            path.add(nums[i]);
//            dfsFullArray(nums, res, path, used);
//            path.remove(path.size() - 1);
//            used[i] = false; //标明这个数字没有使用
//        }

        //-----------------------------------------
        //上面注释掉的是版本1
        //版本2
        //相当于这里判断dfs深度极限，提前了一级。
        if (nums.length - 1== path.size()) {
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) {
                    path.add(nums[i]);
                    res.add(new LinkedList<>(path));
                }
            }
            path.remove(path.size() - 1);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 如果这一位数字已经使用了
            if (used[i] == true) {
                continue;
            }

            used[i] = true; //标明这个数字已经使用了
            path.add(nums[i]);
            dfsFullArray(nums, res, path, used);
            path.remove(path.size() - 1);
            used[i] = false; //标明这个数字没有使用
        }
    }
}
