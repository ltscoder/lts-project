package com.lts.arth.backtraking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/7
 * @menu 组合 leecode 77
 */
public class B3combine {


    /**
     * 给定两个整数 n 和 k，返回 1 … n 中所有可能的 k 个数的组合。
     * 回溯(中剪枝优化) +dfs。
     * @param args
     */
    public static void main(String[] args) {
        int n = 5;
        int k = 3;

        List<List<Integer>> solution = solution(n, k);
        System.out.println(solution);

        //扩展，给定一个int数组，和k，返回所有可能的 k 个数的组合。
        int[] input = {1,2,2,2,3,4};
        //排序
        Arrays.sort(input);
        k = 3;
        List<List<Integer>> lists = solution2(input, 3);
        System.out.println(lists);


    }


    public static List<List<Integer>> solution(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        if (n == 0 || k > n) {
            return ret;
        }
        List<Integer> path = new ArrayList<>();
        dfsCombine(n, k, 1, ret, path);
        return ret;
    }


    /**
     *~考虑按树从顶层向下，每一层确定下一个位置的数，dfs+回溯，注意下一层选的数要比上一层的大，
     * @param n 给定的n，1，2...n
     * @param k k个数的组合。
     * @param index 当前index
     * @param ret 返回结果
     * @param path 路径
     */
    private static void dfsCombine(int n, int k, int index, List<List<Integer>> ret, List<Integer> path) {

        if (path.size() == k) {
            ret.add(new ArrayList<>(path));
            return;
        }

        //对for循环的上线进行限制，令x为最大的for循环值，当i>x时，没有足够的数，能凑够k个了，~剪枝。
        //有关系path.size() + (n - x + 1) = k --》 x = path.size() + n - k + 1;
        for (int i = index; i <= path.size() + n - k + 1; i++) {

            path.add(i);
            //对下层for循环的下线进行限制，i+1，即下一层选的数要比上一层的大
            dfsCombine(n, k, i+1, ret, path);
            path.remove(path.size() - 1);
        }
    }



    public static List<List<Integer>> solution2(int[] input, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        if (input.length ==  0 || k > input.length) {
            return ret;
        }
        List<Integer> path = new ArrayList<>();
        int[] used = new int[input.length];
        dfsCombine2(input, k, 0, ret, path, used);
        return ret;
    }

    /**
     *~考虑按树从顶层向下，每一层确定下一个位置的数，dfs+回溯，注意下一层选的数要比上一层的大，
     * @param input 给定的数组
     * @param k k个数的组合。
     * @param index 当前index
     * @param ret 返回结果
     * @param path 路径
     */
    private static void dfsCombine2(int[] input, int k, int index, List<List<Integer>> ret, List<Integer> path, int[] used) {

        if (path.size() == k) {
            ret.add(new ArrayList<>(path));
            return;
        }

        //排序+for循环从上一个index+1开始，解决重复的组合。
        //对for循环的上线进行限制，令x为最大的for循环值的序号，当i>x时，没有足够的数，能凑够k个了，~剪枝。
        //有关系path.size() + (n-1 - x + 1) = k --》 x = path.size() + n - k;
        for (int i = index; i <= path.size() + input.length - k; i++) {
            //解决重复的排列。方式1：排序+(nums[i] == nums[i - 1] && used[i - 1] == 0)判断,
//            if (i > 0 && input[i] == input[i-1] && used[i-1] == 0){
//                continue;
//            }

            //解决重复的排列。方式2：i > index && input[i] == input[i-1],
            //可以不用used这个变量，因为i是从index开始的，used[i-1]肯定==0。
            if (i > index && input[i] == input[i-1]) {
                continue;
            }
            path.add(input[i]);
            used[i] = 1;
            //对下层for循环的下线进行限制，i+1，即下一层选的数要比上一层的大
            dfsCombine2(input, k, i+1, ret, path, used);
            used[i] = 0;
            path.remove(path.size() - 1);
        }
    }

}
