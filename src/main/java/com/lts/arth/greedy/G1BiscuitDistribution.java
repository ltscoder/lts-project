package com.lts.arth.greedy;

import java.util.Arrays;

/**
 * @author luotianshun
 * @date 2021/2/20
 * @menu 饼干分发 lc 455
 */
public class G1BiscuitDistribution {

    /**
     * 给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；
     * 并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
     * 你的目标尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * 贪心思想
     *
     *
     * 题目分析：
     * 　　使用贪心算法，对孩子需求数组和饼干大小数组分别进行从小到大的排序。然后按照从小到大的顺序使用各个饼干尝试满足某个孩子，
     * 每个饼干只是用一次，若尝试成功的话，孩子个数加一，并换下一个孩子接着进行后面的尝试，直到发现没有更多的孩子或者没有更多的饼干为止，
     * 循环结束，返回此时的孩子个数。
     * @param args
     */
    public static void main(String[] args) {
        //小孩的胃口值
        int[] children = {1,2};
        //饼干的尺寸值
        int[] biscuit = {1,2,3};
        int num = solution(children, biscuit);
        System.out.println(num);
    }

    private static int solution(int[] children, int[] biscuit) {
        //都排序
        Arrays.sort(children);
        Arrays.sort(biscuit);

        int num = 0;
        for (int ci = 0, bi = 0; ci < children.length && bi < biscuit.length;) {
            //如果饼干满足小孩胃口值，则小孩序号加1，num+1
            if (children[ci] <= biscuit[bi]) {
                ci++;
                num++;
            }
            //饼干序号始终+1
            bi++;
        }
        return num;
    }
}
