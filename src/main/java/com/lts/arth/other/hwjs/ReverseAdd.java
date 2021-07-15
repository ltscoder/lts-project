package com.lts.arth.other.hwjs;

/**
 * @author luotianshun
 * @date 2021/7/14
 * @menu 整数反转求和
 * 请你写一个reverseAdd函数，该函数根据输入的两个正整数a和b，然后分别将他们的数字按照高位在右边的方式反转后求和。
 * 例如reverseAdd(123, 456) = 321 + 654 = 975
 * 输入参数有效取值范围在[1, 70000]区间的正整数
 */
public class ReverseAdd {

    public static void main(String[] args) {
        int a = 123;
        int b = 456;
        //进制转换知识点
        int result = solution(a, b);
        System.out.println(result);
    }

    private static int solution(int a, int b) {
        if ((a < 1 || a > 70000 || b < 1 || b > 70000)) {
            return -1;
        }
        int num1 = reverseNum(a);
        int num2 = reverseNum(b);
        return num1 + num2;
    }

    /**
     * 将一个数反转
     * @param num
     * @return
     */
    private static int reverseNum(int num) {
        int a = 0;
        while (num != 0) {
            a = a * 10 + num % 10;
            num = num / 10;
        }
        return a;
    }
}
