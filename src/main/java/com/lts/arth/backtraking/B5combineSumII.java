package com.lts.arth.backtraking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/8
 * @menu  组合总和2 leecode 40
 */
public class B5combineSumII {

    /**
     * 给定一个有重复元素数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 回溯(中剪枝优化) +dfs。
     * @param args
     */
    public static void main(String[] args) {

        //candidates，有重复的元素。
        int[] candidates = {1,2,2,3,3,4,5};
        int target = 6;
        //排序，~因为是返回组合(与顺序无关)，所以先排序
        Arrays.sort(candidates);
        List<List<Integer>> lists = solution(candidates, target);
        System.out.println(lists);

    }

    private static List<List<Integer>> solution(int[] input, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        if (input.length == 0) {
            return ret;
        }
        List<Integer> path = new ArrayList<>();
        dfsCombineSumII(input, sum, 0, ret, path);
        return ret;
    }


    /**
     *
     * @param input 排序后的数组
     * @param sumLeft 剩余的数量
     * @param index 索引位置
     * @param ret 结果集
     * @param path 遍历路径
     */
    private static void dfsCombineSumII(int[] input, int sumLeft, int index, List<List<Integer>> ret, List<Integer> path) {
        if (sumLeft == 0) {
            ret.add(new ArrayList<>(path));
            return;
        }
        //1因为返回的组合，且同一个元素只能用一次，递归位置index，与上一级相同的index位置开始
        //2剪枝，当sumLeft - input[i]<0时，就不必继续了，直接return。
        for (int i = index; i < input.length && sumLeft - input[i] >= 0; i++) {
            //去重，解决重复的排列
            if (i > index && input[i] == input[i - 1]) {
                continue;
            }
            path.add(input[i]);
            dfsCombineSumII(input, sumLeft - input[i], i + 1, ret, path);
            path.remove(path.size() - 1);
        }


    }


}
