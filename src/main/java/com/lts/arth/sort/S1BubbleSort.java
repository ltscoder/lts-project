package com.lts.arth.sort;


/**
 * @author luotianshun
 * @date 2021/7/15
 * @menu 冒泡排序
 * 比较相邻的元素，如果前一个比后一个大，交换之。
 * 第一趟排序第1个和第2个一对，比较与交换，随后第2个和第3个一对比较交换，这样直到倒数第2个和最后1个，将最大的数移动到最后一位。
 * 第二趟将第二大的数移动至倒数第二位
 * ......
 * 因此需要n-1趟；
 *参考：https://www.jianshu.com/p/1458abf81adf
 */
public class S1BubbleSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.getSortInput();
        solution(arr);
        ArrayUtil.printArr(arr);
    }

    private static void solution(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {   //需要遍历length-1次
            for (int j = 0; j < arr.length - i - 1; j++) { //每次从index=0开始，到arr.length - i - 1
                if (arr[j] > arr[j+1]) {
                    ArrayUtil.swap(arr, j, j + 1);
                }
            }
        }
    }
}
