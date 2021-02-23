package com.lts.arth.greedy;

/**
 * @author luotianshun
 * @date 2021/2/20
 * @menu 移掉K位数字 lc 402
 */
public class G5RemoveKDigits {

    /**
     * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
     * 注意:
     * num 的长度小于 10002 且 ≥ k。
     * num 不会包含任何前导零。
     *
     * 示例 1 :
     * 输入: num = "1432219", k = 3
     * 输出: "1219"
     * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
     * 示例 2 :
     * 输入: num = "10200", k = 1
     * 输出: "200"
     * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
     * 示例 3 :
     * 输入: num = "10", k = 2
     * 输出: "0"
     * 解释: 从原数字移除所有的数字，剩余为空就是0。
     *
     * 贪心算法
     * 参考【移掉K位数字图文详解】 by 数据结构和算法
     * @param args
     */
    public static void main(String[] args) {

        //test1
        String num = "1432219";
        int k = 3;
        String result = solution(num, k);
        System.out.println(result);

        //test2
        num = "10200";
        k = 1;
        result = solution(num, k);
        System.out.println(result);

        //test3
        num = "10";
        k = 2;
        result = solution(num, k);
        System.out.println(result);
    }


    private static String solution(String num, int k) {
        return removeKDigits(num, k);
    }

    /**
     * 贪心思想
     * @param num 输入字符串
     * @param k 输入移除k个
     * @return
     */
    private static String removeKDigits(String num, int k) {

        if (num.length() <= k) {
            return "0";
        }
        //移除k个数后，字符串还剩多少个数。
        int digits = num.length() - k;
        //创建一个栈(模拟栈)，存放结果
        char[] stk = new char[digits];
        //栈顶，top位置没有元素。
        int top = 0;

        //把原始字符串中的字符逐个放到stk数组中，放的时候如果发现前面有比他大的，就把前面比他大的移除掉
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            //发现前一个元素比当前元素大，则移除前一个元素(top--)。
            if (top > 0 && stk[top - 1] > c && k > 0) {
                k--;
                top--;
            }
            //放入当前元素
            if (top < digits) {
                stk[top++] = c;
            } else {
                //栈已经满了，则do nothing。
                k--;
            }
        }

        //移除前导零
        int idx = 0;
        while (idx < digits && stk[idx] == '0') {
            idx++;
        }
        //返回结果
        return idx == digits ? "0" : new String(stk, idx, digits -idx);
    }
}
