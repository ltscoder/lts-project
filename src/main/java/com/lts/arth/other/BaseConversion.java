package com.lts.arth.other;

import org.junit.Test;

/**
 * @author luotianshun
 * @date 2021/7/13
 * @menu 进制转换
 *
 * 其它：
 * @see com.lts.arth.other.hwjs.ReverseAdd
 */
public class BaseConversion {


    /**
     * 大数相加。用0-9A-Za-z表示62进制。做加法
     * ascii码对照表：0-48，A-65,a-97
     * 20210712的字节面试题
     */
    @Test
    public void base62Add() {
        //
        String a = "bz5";
        String b = "5C5";
        //方法1，先转成10进制，相加，再转成62进制。面试的时候写的，编译没通过，面试后又改的。
        //因为题目中说大数相加，可能溢出，所以此方法不可取。
        System.out.println(convert(a, b));

        //方法2，ascll码相加。面试后写的。
        System.out.println(asciiConvert(a, b));
    }

    /**
     *  结果
     * @param a 62进制加数1
     * @param b 62进制加数2
     * 用ascii相加
     *
     * ~：将值转为0-61的ascii码值：        bValue - 'A' + 10
     *   将0-61的ascii码值转为0-9A-Za-z： add - 10 + 'A'
     *
     * @return
     */
    private String asciiConvert(String a, String b) {
        int maxLength = Math.max(a.length(), b.length());
        String res = "";
        int advance = 0;
        //从最小位数开始计算
        for (int i = maxLength - 1; i >= 0 || advance == 1; i--) {
            //取每一位的值
            char aValue = i >= 0 && a.length() > i ? a.charAt(i) : '0';
            char bValue = i >= 0 && b.length() > i ? b.charAt(i) : '0';

            //将值转为0-61的ascii码值
            if (aValue >= '0' && aValue <= '9') {
                aValue -= 48;
            } else if (aValue >= 'A' && aValue <= 'Z') {
                aValue = (char) (aValue - 'A' + 10);
            } else if (aValue >= 'a' && aValue <= 'z') {
                aValue = (char) (aValue - 'a' + 36);
            }
            if (bValue >= '0' && bValue <= '9') {
                bValue -= 48;
            } else if (bValue >= 'A' && bValue <= 'Z') {
                bValue = (char) (bValue - 'A' + 10);
            } else if (bValue >= 'a' && bValue <= 'z') {
                bValue = (char) (bValue - 'a' + 36);
            }

            //计算两个数和进位的和
            int add = aValue + bValue + advance;
            if (add > 61) {
                advance = 1;
                add -= 62;
            } else {
                advance = 0;
            }
            //将0-61的ascii码值转为0-9A-Za-z，并拼到结果res的前面
            if (add >= 0 && add < 10) {
                res = add + res;
            } else if (add >= 10 && add < 36) {
                res = (char)(add - 10 + 'A') + res;
            } else if (add >= 36 && add < 62) {
                res = (char)(add - 36 + 'a') + res;
            }
        }
        return res;
     }

    /**
     *  结果
     * @param a 62进制加数1
     * @param b 62进制加数2
     * 自己面试的时候写的
     * @return
     */
    private String convert(String a, String b) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int intValue = 0;
        int times = 1;
        for (int i = a.length() - 1; i >= 0; i--) {
            intValue += chars.indexOf(a.charAt(i)) * times;
            times *= 62;
        }
        times = 1;
        for (int i = b.length() - 1; i >= 0; i--) {
            intValue += chars.indexOf(b.charAt(i)) * times;
            times *= 62;
        }
        String strValue = "";
        while (intValue != 0) {
            strValue = chars.charAt(intValue % 62) + strValue;
            intValue /= 62;
        }
        if (strValue.length() == 0) {
            strValue = "0";
        }
        return strValue;


    }
}
