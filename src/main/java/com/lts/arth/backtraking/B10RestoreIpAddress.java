package com.lts.arth.backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/8
 * @menu 复原ip地址 leecode 93
 */
public class B10RestoreIpAddress {

    /**
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     * 回溯+dfs。
     * 参考【[LeetCode] 93. 复原IP地址】 by 威行天下
     * 输入: "25525511135"
     * 输出: ["255.255.11.135", "255.255.111.35"]
     * 我们要知道IP的格式,每位是在0~255之间,
     * 注意: 不能出现以0开头的两位以上数字,比如012,08
     * @param args
     */
    public static void main(String[] args) {

        String input = "25525511135";
        List<String> result = solution(input);
        System.out.println(result);
    }

    private static List<String> solution(String input) {
        List<String> result = new ArrayList<>();
        if (input.length() < 4) {
            return result;
        }

        String path = "";
        dfsRestoreIpAddress(input, 0, 4, result, path);
        return result;
    }

    /**
     *
     * @param input 输入的字符串
     * @param index 当前这一层开始遍历的索引位置。
     * @param depthLeft 当前path剩余的深度，最大为4
     * @param result 结果集
     * @param path 遍历的路径
     */
    private static void dfsRestoreIpAddress(String input, int index, int depthLeft, List<String> result, String path) {
        //如果path里已经有四个了，且刚好用完了input，则加入结果，返回
        if (depthLeft == 0 && index == input.length()) {
            result.add(path.substring(0, path.length() - 1));
            return;
        }
        //已经四个了，input中还有数字没用完，不符合要求，返回。
        if (depthLeft == 0) {
            return;
        }
        //遍历3次
        for (int i = index; i < index + 3; i++) {
            if (i < input.length()) {
                //ip校验，只能是单独的“0”，
                if (i == index && input.charAt(i) == '0') {
                    dfsRestoreIpAddress(input, i + 1, depthLeft - 1, result, path + input.charAt(i) + ",");
                    break;
                }
                //ip校验 ip每个位置<255
                if (Integer.valueOf(input.substring(index, i + 1)) <= 255) {
                    //这里回溯
                    dfsRestoreIpAddress(input, i + 1, depthLeft - 1, result, path + input.substring(index, i + 1) + ".");
                }
            }
        }
    }
}
