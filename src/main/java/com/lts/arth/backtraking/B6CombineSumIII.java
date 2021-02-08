package com.lts.arth.backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/8
 * @menu  组合总和3 leecode 216
 */
public class B6CombineSumIII {

    /**
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * 回溯(中剪枝优化) +dfs。
     * @param args
     */
    public static void main(String[] args) {
        int n = 7;
        int k = 3;

        List<List<Integer>> lists = solution(n, k);
        System.out.println(lists);
    }

    /**
     *
     * @param sum 总和
     * @param nums 组合的元素个数
     * @return
     */
    private static List<List<Integer>> solution(int sum, int nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (sum < 1 || nums < 1) {
            return ret;
        }
        List<Integer> path = new ArrayList<>();
        dfsCombineSumIII(sum, nums,1, ret, path);
        return ret;

    }

    /**
     *  @param sumLeft 总和剩余
     * @param numsLeft 组合元素个数剩余数量
     * @param index 1-9中的数字位置
     * @param ret 结果
     * @param path 遍历的路径
     */
    private static void dfsCombineSumIII(int sumLeft, int numsLeft, int index, List<List<Integer>> ret, List<Integer> path) {
        if (sumLeft == 0) {
            //总和满足要求，元素个数也满足要求，则加入结果集
            if (0 == numsLeft) {
                ret.add(new ArrayList<>(path));
            }
            return;
        }

        //剪枝，1因为元素个数需满足要求，所以i <= 9 - numsLeft +1；2当sumLeft - i<0,也不用继续了
        for (int i = index; i <= 9 - numsLeft +1 && sumLeft - i >= 0; i++) {
            path.add(i);
            dfsCombineSumIII(sumLeft - i, numsLeft - 1, i + 1, ret, path);
            path.remove(path.size() - 1);
        }
    }
}
