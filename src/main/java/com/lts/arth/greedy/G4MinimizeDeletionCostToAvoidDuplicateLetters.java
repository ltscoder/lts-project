package com.lts.arth.greedy;

/**
 * @author luotianshun
 * @date 2021/2/20
 * @menu 避免重复字母的最小删除成本 lc 1578
 */
public class G4MinimizeDeletionCostToAvoidDuplicateLetters {

    /**
     * 给你一个字符串 s 和一个整数数组 cost ，其中 cost[i] 是从 s 中删除字符 i 的代价。
     * 返回使字符串任意相邻两个字母不相同的最小删除成本。
     * 请注意，删除一个字符后，删除其他字符的成本不会改变。
     *
     *输入：s = "aabaa", cost = [1,2,3,4,1]
     *输出：2
     *解释：删除第一个和最后一个字母，得到字符串 ("aba") 。
     *
     * 参考【leetcode1578.避免重复字母的最小删除成本】 by 大腿壮
     * 双指针+贪心
     * @param args
     */
    public static void main(String[] args) {

        String str = "aabaa";
        int[] cost = {1,2,3,4,1};

        int result = solution(str, cost);
        System.out.println(result);
    }

    /**
     *
     * @param str 输入的str
     * @param cost str每个字符的删除花销
     * @return
     */
    private static int solution(String str, int[] cost) {

        int minCost = minimizeDeletionCostToAvoidDuplicateLetters(str, cost);
        return minCost;
    }

    /**
     * 双指针+贪心
     * @param str 输入的字符串
     * @param cost 输入的删除每个字符的花销
     * @return 最小花销
     */
    private static int minimizeDeletionCostToAvoidDuplicateLetters(String str, int[] cost) {
        //slow留下的后一个元素序号，fast前一个元素序号，minCost花销
        int slow = 0, fast = 1, length = str.length(), minCost = 0;
        char[] chars = str.toCharArray();
        if (str.length() == 1) {
            return minCost;
        }

        //遍历整个数组
        while (fast<length) {
            //与前一个不重复，则fast++
            if (chars[slow] != chars[fast]) {
                slow = fast;
            } else {
                //与前一个重复，删除花销小的
                if (cost[slow] < cost[fast]) {
                    minCost += cost[slow];
                    slow = fast;
                } else {
                    minCost += cost[fast];
                }
            }
            fast++;
        }
        return minCost;
    }
}
