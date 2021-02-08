package com.lts.arth.backtraking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/7
 * @menu 全排列2 leecode 47
 */
public class B2FullArrayII {

    /**
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * 思想 1回溯法+dfs  2解法2交换元素，略
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] input = {1, 1, 2, 3};
        //回溯法
        List<List<Integer>> solution = solution(input);
        System.out.println(solution);

        //元素交换法，略
    }

    public static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return list;
        int[] visited = new int[nums.length];
        //先排序
        Arrays.sort(nums);
        dfsFullArrayII(nums, visited, new ArrayList(), list);
        return list;
    }

    /**
     * 生成全排列
     * ~考虑按树从顶层向下，每一层确定下一个位置的数，dfs+回溯
     *
     * @param nums 全排列的数字
     * @param res  结果变量
     * @param path 每一种组合
     * @param used 对应nums中的每位数字是否已经使用
     */
    public static void dfsFullArrayII(int[] nums, int[] used, List<Integer> path, List<List<Integer>> res) {
        //nums.length == path.size(),说明nums被用完了，加入结果
        //~扩展，这里nums.length可以换成n，n<nums.length(),表示返回所有不重复的n个数的全排列。
        if (path.size() == nums.length) {
            res.add(new ArrayList(path));
        }
        for (int i = 0; i < nums.length; i++) {
            //当前值等于前一个值，两种情况
            //1num[i-1]没用过说明回溯到了同一层，此时接着用num[i],相当于同层用用一个值用了两次，重复了，continue
            //2num[i-1]用过了，说明num[i]在num[i-1]的下一层，相等不会重复。
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0)//去重的重点！！！！！
                continue;
            if (used[i] == 1)
                continue;
            used[i] = 1;
            path.add(nums[i]);
            dfsFullArrayII(nums, used, path, res);
            path.remove(path.size() - 1);
            used[i] = 0;
        }
    }
}

