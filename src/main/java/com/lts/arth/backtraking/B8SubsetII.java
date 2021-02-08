package com.lts.arth.backtraking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/8
 * @menu 子集2 leecocde 90
 */
public class B8SubsetII {

    /**
     * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 回溯(中剪枝优化) +dfs。
     * 输入: [1,2,2]
     * 输出:
     * [
     * [2],
     * [1],
     * [1,2,2],
     * [2,2],
     * [1,2],
     * []
     * ]
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] input = {2, 1, 2};

        List<List<Integer>> result = solution(input);
        System.out.println(result);
    }

    private static List<List<Integer>> solution(int[] input) {
        List<List<Integer>> ret = new ArrayList<>();
        if (input.length == 0) {
            return ret;
        }
        //排序，~因为是返回组合(与顺序无关)，所以先排序
        Arrays.sort(input);
        List<Integer> path = new ArrayList<>();
        dfsSubsetII(input, 0, ret, path);
        return ret;
    }

    /**
     * @param input 排序的元素
     * @param index 当前这一层开始遍历的索引位置。
     * @param ret   结果集
     * @param path  遍历的路径
     */
    private static void dfsSubsetII(int[] input, int index, List<List<Integer>> ret, List<Integer> path) {
        ret.add(new ArrayList<>(path));
        for (int i = index; i < input.length; i++) {
            //去重，解决重复的排列
            if (i > index && input[i] == input[i - 1]) {
                continue;
            }
            path.add(input[i]);
            dfsSubsetII(input, i + 1, ret, path);
            path.remove(path.size() - 1);
        }
    }
}
