package com.lts.arth.other.hwjs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/7/14
 * @menu 经理出差
 * 题目参考【华为机试题整理】by 一禅的师兄 https://blog.csdn.net/weixin_42145502/article/details/107964633
 * 小k是x区域销售经理，他平时常驻5城市，并且经常要到1，2，3，4，6城市出差，当机场出现大雾时，会导致对应城市的所有航班起飞及降落均停止
 * (即不能从该城市出发，其它城市也不能到达该城市)。小k希望知道如果他需要到x城市出差时，如果遇到y城市大雾，他最短飞行时间及飞行路径。
 * 注意：当两个城市间不可达时，消耗时间默认取1000.
 * 各城市间的飞行时间如下。M代表不可达
 * 1->4花费5小时，4->1花费2小时
 * 5->3不可达，3->5花费7小时
 * 1 2 3 4 5 6
 * 1 0 2 10 5 3 M
 * 2 M 0 12 M M 10
 * 3 M M 0h M 7h M
 * 4 2h M M 0h 2h M
 * 5 4h M M 1h 0h M
 * 6 3h M 1h M 2h 0h
 *
 * 输入描述：
 * 输入出差城市x(x可为1，2，3，4，6)
 * 输入大雾城市y(y可为0，1，2，3，4，5，6，为0代表没有城市出现大雾)
 */
public class ManagerTrip {

    public static void main(String[] args) {
        int x = 4;
        int y = 3;
        //6个站，初始7*7的数组
        int[][] arr = {
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, 0, 2, 10, 5, 3, -1},
                {-1, -1, 0, 12, -1, -1, 10},
                {-1, -1, -1, 0, -1, 7, -1},
                {-1, 2, -1, -1, 0, 2, -1},
                {-1, 4, -1, -1, 1, 0, -1},
                {-1, 3, -1, 1, -1, 2, 0},
        };

        //回溯dfs->类似求排列
        int result = solution(x, y, arr);
    }

    /**
     * by me
     * @param x 出差城市
     * @param y 大雾城市
     * @param arr 各城市飞行时间
     * @return
     */
    private static int solution(int x, int y, int[][] arr) {
        if (x == 5) {
            return 0;
        }
        //处理大雾城市，置为不可达。
        for (int i = 0; i <=6; i++) {
            arr[i][y] = -1;
            arr[y][i] = -1;
        }

        boolean[] used = new boolean[7];
        used[0] = true;
        used[5] = true;
        //深度遍历时花费的时间
        int time = 0;
        //最短时间
        int shortestTime = 1000;
        //深度遍历时路径，
        List<Integer> path = new ArrayList<>();
        path.add(5);
        //最短路径
        List<Integer> shortestPath = new ArrayList<>();

        dfsFindShortestPath(x, used, time, shortestTime, path, shortestPath, arr);
        return 0;

    }

    /**
     * dfs寻找最短路径
     * @param x 目的城市
     * @param used 已经经过的城市
     * @param time 花费的时间
     * @param shortestTime 目前最短路径
     * @param path 路径
     * @param shortestPath 目前最短路径
     * @param arr 城市飞行时间
     * by me 应该是对的
     * 类似求排列。
     *
     */
    private static void dfsFindShortestPath(int x, boolean[] used, int time, int shortestTime, List<Integer> path, List<Integer> shortestPath, int[][] arr) {
        //判断如果上一个城市是x，则判断是否最短路径，并返回
        if (path.get(path.size() - 1) == x) {
            if (shortestTime > time) {
                shortestTime = time;
                shortestPath = new ArrayList<>(path);
                System.out.println(shortestTime);
                System.out.println(shortestPath);
                //shortestTime和shortestPath可以封装成map返回，略
            }
            return;
        }
        for (int i = 1; i <= 6; i++) {
            //如果已经经过i城市了，获取上一个城市->i城市不可达，contine
            if (used[i] || arr[path.get(path.size() - 1)][i] == -1) {
                continue;
            }
            //回溯
            used[i] = true;
            time = time + arr[path.get(path.size() - 1)][i];  //注意这里有顺序，time先加，再path.add(i)
            path.add(i);
            dfsFindShortestPath(x, used, time, shortestTime, path, shortestPath, arr);
            used[i] = false;
            path.remove(path.size() - 1);
            time = time - arr[path.get(path.size() - 1)][i];
        }
    }
}
