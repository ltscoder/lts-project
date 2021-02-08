package com.lts.arth.backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/8
 * @menu 括号生成 leecode 22
 */
public class B11BracketGeneration {

    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     * 例如，给出 n = 3，生成结果为：
     * <p>
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     *
     * @param args
     */
    public static void main(String[] args) {


        int n = 3;
        //这个方法不行。
        List<String> result = solution(n);
        System.out.println(result);

    }


    private static List<String> solution(int n) {
        List<String> result = new ArrayList<>();

        if (n <= 0) {
            return result;
        }

        String path = "";
        dfsBracketGeneration(n, result, path);
        return result;
    }

    /**
     * @param nLeft  还要加多少个括号（）
     * @param result 结果集
     * @param path   回溯的path
     */
    private static void dfsBracketGeneration(int nLeft, List<String> result, String path) {
        if (nLeft == 0) {
            result.add(path);
            return;
        }
        for (int i = 0; i <= path.length(); i++) {
            String pre = path.substring(0, i);
            String suf;
            if (i < path.length()) {
                suf = path.substring(i, path.length());
            } else {
                suf = "";
            }
            dfsBracketGeneration(nLeft - 1, result, pre + "()" + suf);
        }
    }
}
