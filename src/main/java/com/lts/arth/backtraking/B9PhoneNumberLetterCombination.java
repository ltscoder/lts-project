package com.lts.arth.backtraking;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/8
 * @menu 电话号码的字母组合 leecode 17
 */
public class B9PhoneNumberLetterCombination {

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 回溯+dfs。
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * 参考【17. 电话号码的字母组合】 by tqz
     * @param args
     */

    private static String[] alphabet = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    public static void main(String[] args) {

        String input = "23";
        List<String> result = solution(input);
        System.out.println(result);
    }

    private static List<String> solution(String input) {
        List<String> result = new ArrayList<>();
        if (StringUtils.isEmpty(input)) {
            return result;
        }
        String path = "";
        dfsPhoneNumberLetterCombination(input, 0, result, path);
        return result;
    }

    /**
     *  @param input 输入的2-9字符串
     * @param index 当前这一层开始遍历的索引位置。
     * @param result 结果集
     * @param path 遍历路径
     */
    private static void dfsPhoneNumberLetterCombination(String input, int index, List<String> result, String path) {

        if (path.length() == input.length()) {
            result.add(path);
            return;
        }

        //~这里B1-B8，深度和广度遍历的维度都是一样的
        //~而这里深度遍历是对input。广度是对alphabet[i]，最后返回的path是从alphabet中获得的。
        int i = input.charAt(index) - '0';
        for (int j = 0; j < alphabet[i].length(); j++) {
            //这里回溯
            dfsPhoneNumberLetterCombination(input, index + 1, result, path + alphabet[i].charAt(j));
        }
    }
}
