package com.lts.arth.other.hwjs;

/**
 * @author luotianshun
 * @date 2021/7/14
 * @menu 在字符串中找出连续最长的数字串
 * 请一个在字符串中找出连续最长的数字串，并把这个串的长度返回；如果存在长度相同的连续数字串，返回最后一个连续数字串；
 * 注意：数字串只需要是数字组成的就可以，并不要求顺序，比如数字串“1234”的长度就小于数字串“1359055”，
 * 如果没有数字，则返回空字符串（“”）而不是NULL！（说明：不需要考虑负数）
 */
public class FindLongestNum {

    public static void main(String[] args) {
        String input = "abcd12345ed125ss123058789d";
        String result = solution(input);
        System.out.println(result);
    }

    private static String solution(String input) {
        //方法一，by me
        //~贪心
        String longestNum = "";
        String cur = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '0' && c <= '9') {
                cur += c;
            } else {
                if (cur.length() >= longestNum.length()) {
                    longestNum = cur;
                }
                cur = "";
            }
        }
        return longestNum;

//        方法二,和方法一差不多，不用看
//        int maxLength = 0;
//        int cur = 0;
//        String longestNum = "";
//        for (int i = 0; i < input.length(); i++) {
//            char c = input.charAt(i);
//            if (c >= '0' && c <= '9') {
//                cur++;
//            } else {
//                if (cur >= maxLength && cur != 0) {
//                    maxLength = cur;
//                    longestNum = input.substring(i - cur, i);
//                }
//                cur = 0;
//            }
//        }
//        return longestNum;
    }
}
