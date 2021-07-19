package com.lts.arth.sort;

/**
 * @author luotianshun
 * @date 2021/7/16
 * @menu 选择排序
 */
public class S5SelectSort {
    public static void main(String[] args) {
        int[] arr = ArrayUtil.getSortInput();
        solution(arr);
        ArrayUtil.printArr(arr);
    }

    private static void solution(int[] arr) {
        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {//每次找最小的，需要找length-1次
            minIndex = i;
            for (int j = i; j < arr.length; j++) {//从i开始遍历，找到最小数的index
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            ArrayUtil.swap(arr, i, minIndex);
        }
    }
}
