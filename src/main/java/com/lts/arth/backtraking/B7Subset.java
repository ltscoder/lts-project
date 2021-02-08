package com.lts.arth.backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/8
 * @menu 子集 leecode 78
 */
public class B7Subset {

    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     * 回溯 +dfs。
     * @param args
     */
    public static void main(String[] args) {

        int[] input = {1,2,3};
        List<List<Integer>> result = solution(input);
        System.out.println(result);
    }

    private static List<List<Integer>> solution(int[] input) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        //不用排序，没有重复元素
        dfsSubset(input, 0, ret, path);
        return ret;
    }

    /**
     * 
     * @param input 输入数组
     * @param index 当前这一层开始遍历的索引位置。
     * @param ret 结果集
     * @param path 遍历的路径
     */
    private static void dfsSubset(int[] input, int index, List<List<Integer>> ret, List<Integer> path) {
        ret.add(new ArrayList<>(path));

        for (int i = index; i < input.length; i++) {
            path.add(input[i]);
            dfsSubset(input, i + 1, ret, path);
            path.remove(path.size() - 1);
        }
    }
}
