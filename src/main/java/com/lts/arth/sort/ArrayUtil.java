package com.lts.arth.sort;

import java.util.Arrays;

/**
 * @author luotianshun
 * @date 2021/7/16
 * @menu
 */
public class ArrayUtil {

    public static int[] sortArr = {2,5,1,3,8,5,7,4,3,9};

    public static void printArr(int[] arr) {
        String res = "";
        for (int a: arr) {
            res = res + a + ",";
        }
        System.out.println(res.substring(0, res.length() - 1));
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] getSortInput() {
        return Arrays.copyOf(sortArr,sortArr.length);
    }
}
