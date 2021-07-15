package com.lts.arth.other;

/**
 * @author luotianshun
 * @date 2021/7/15
 * @menu 最长回文字符串
 * 输入一个字符传，返回最长的回文串
 */
public class LongestPalindrome {

    public static int max = 0;
    public static String maxPalindrome = "";

    public static void main(String[] args) {
        String s = "fsdsgggiowendfdnefhfhwee";
        //方法1,假设回文长度为s.length。失败再假设回文长度为s.length - 1。
        String result = solution(s);
        System.out.println(result);

        //方法2，中心扩散。
        result = solution2(s);
        System.out.println(result);

        //方法3，动态规划
        result = solution3(s);
        System.out.println(result);

        //方法4，Manacher's Algorithm(马拉车算法)，~字符中间插入#，略
    }

    /**
     * 动态规划
     * @param s
     * @return
     * by me
     */
    private static String solution3(String s) {
        if (s.length() == 1) {
            return s;
        }
        if (s.length() == 0) {
            return "";
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        max = 1;
        maxPalindrome = s.charAt(0) + "";
        //状态转移方程
        //dp[i][j] =  true  i=j;
        //            str[i] == str[j]  j - i = 1
        //            str[i] == str[j] && dp[i+1][j-1]

        //回文长度k从1开始
        for (int k = 1; k <= s.length(); k++) {
            for (int i = 0; i <= s.length() - k; i++) {
                int low = i, high = i + k - 1;
                if (s.charAt(low) == s.charAt(high) && (k <= 2 || dp[low + 1][high - 1])) { // 回文串的动态转移关系
                    dp[low][high] = true;
                    if (max < k) {
                        max = k;
                        maxPalindrome = s.substring(i, i + k);
                    }
                }
            }
        }
        return maxPalindrome;
    }

    private static String solution2(String s) {
        if (s.length() == 1) {
            return s;
        }
        if (s.length() == 0) {
            return "";
        }

        for (int i = 0; i < s.length() - 1; i++) {
            findLongestPalindrome(s, i, i);//单核回文
            findLongestPalindrome(s, i, i + 1);//双核回文
        }
        return maxPalindrome;
    }

    /**
     * 以low，high为中心，寻找最大回文串
     * @param s
     * @param low
     * @param high
     */
    private static void findLongestPalindrome(String s, int low, int high) {
        while (low >= 0 && high <= s.length() - 1) {
            if (s.charAt(low) == s.charAt(high)) {
                if (low != high && max < high - low + 1) {
                    max = high - low + 1;
                    maxPalindrome = s.substring(low, high + 1);
                }
                low--;
                high++;
            } else {
                break;
            }
        }
    }

    /**
     * ~贪心，先从最长的开始判断：先判断有没有长度为s.length()的回文串，再判断有没有s.length()-1长度的。
     * @param s 输入字符串。
     * @return 最长的回文字符串
     */
    private static String solution(String s) {
        if (s.length() == 1) {
            return s;
        }
        if (s.length() == 0) {
            return "";
        }
        for (int i = s.length(); i > 1; i--) { //i回文串的长度，从最长的开始
            for (int j = 0; j <= s.length() - i; j++) { //j,回文串的开始位置
                String sub = s.substring(j, j + i);  //
                if (isPalindrome(sub)) {
                    return sub;
                }
            }

        }
        return "";
    }

    /**
     * 判断是否是回文
     * @param s
     * @return
     */
    private static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return false;
        }
        for (int i = 0; i <= (s.length() - 1) / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
