package com.lts.arth.other.hwjs;

/**
 * @author luotianshun
 * @date 2021/7/14
 * @menu 接啤酒
 * 酒馆里有m个龙头可供顾客们接啤酒，每个龙头每秒的出酒量相等，都是1。现有n名顾客准备接酒，他们初始的接酒顺序已经确定。
 * 将这些顾客按接酒顺序从1到n编号，i号顾客的接酒量为w_i。接酒开始时，1到m号顾客各占一个酒龙头，并同时打开龙头接酒。
 * 当其中某个顾客j完成其接酒量要求wj后，下一名排队等候接酒的顾客k马上接替j顾客的位置开始接酒。
 * 这个换人的过程是瞬间完成的，且没有任何酒的浪费。即j顾客第x秒结束时完成接酒，则k顾客第x+1秒立刻开始接酒。
 * 若当前接酒人数n’不足m，则只有n’个龙头供酒，其它m-n’个龙头关闭。现在给出n名顾客的接酒量，按照上述规则，问所有顾客都接完酒需要多少秒？
 *
 * 输入描述:
 * 输入包括两行,第一行为以空格分割的两个数n和m,分别表示接酒的人数和酒龙头个数,均为正整数。
 * 第二行n个整数w_i(1 <= w_i <= 100)表示每个顾客接酒量输出描述:
 * 如果输入合法输出酒所需总时间(秒)。
 * 注意：最终交付的函数代码中不要向控制台打印输出任何无关信息。
 * 示例1
 * 输入
 * 5 3
 * 4 4 1 2 1
 * 输出
 * 4
 */
public class PickUpBeer {

    public static void main(String[] args) {
        int[] line1 = {5, 3};
        int[] line2 = {4, 4, 1, 2, 1};

        //~贪心
        int result = solution(line1, line2);
        System.out.println(result);
    }

    /**
     * by me
     * @param line1 第一行输入
     * @param line2 第二行输入
     * @return
     */
    private static int solution(int[] line1, int[] line2) {
        int people = line1[0]; //人数
        int longTou = line1[1]; //龙头数
        int result = 0;
        while (true) {
            //按每分钟来，
            //count, 每分钟最多可已接多少啤酒，必须是不同的人。
            int count = longTou;
            for (int i = 0; i < people; i++) {
                if (count > 0 && line2[i] > 0) {
                    count--;
                    line2[i]--;
                }
            }
            //一点啤酒都没接，说明已经人都已经接满了。退出
            if (count == longTou) {
                break;
            }
            //下一分钟
            result++;
        }
        return result;
    }
}
