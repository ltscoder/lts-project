package com.lts.arth.greedy;

/**
 * @author luotianshun
 * @date 2021/2/20
 * @menu 跳跃游戏 lc 45
 */
public class G3JumpingGame {

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 参考【45. 跳跃游戏 II】 by dcdecade
     * 贪心->迭代
     * @param args
     */
    public static void main(String[] args) {
        int[] input = {2,3,1,1,4};
        int result = solution(input);
        System.out.println(result);
    }

    private static int solution(int[] arr) {
        int nums;
        //方法一
        nums = jumpingGame(arr);
        return nums;
    }

    /**
     * 本题思想贪心算法（贪心->迭代）： 下一跳序号，每次选择的优先级是基于下一个位置所能跳跃的最大下标
     * @param nums 输入的数组
     * @return
     */
    private static int jumpingGame(int[] nums) {
        //当前位置序号
        int start = 0;
        //跳的次数
        int count = 0;

        while (start < nums.length - 1) {
            //如果当前位置，下一跳能跳的最远距离，能够到达终点，则return+1；
            if (start + nums[start] >= nums.length - 1) {
                return count+1;
            }
            //计算下一条位置，
            //下个序号+下个序号的值的最大值。
            int max = 0;
            //下个位置的序号
            int nextIndex = 0;
            for (int i = 1; i <= nums[start]; i++) {
                if (start + i + nums[start + i] > max) {
                    max = start + i + nums[start + i];
                    nextIndex = start + i;
                }
            }
            //更新下一个序号，然后count++
            start = nextIndex;
            count++;
        }
        return count;
    }
}
