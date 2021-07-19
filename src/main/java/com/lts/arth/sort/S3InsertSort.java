package com.lts.arth.sort;

/**
 * @author luotianshun
 * @date 2021/7/16
 * @menu (简单)插入排序
 * 1.从数组的第二个数据开始往前比较，即一开始用第二个数和他前面的一个比较，如果 符合条件（比前面的大或者小，自定义），则让他们交换位置。
 * 2,再对数组的第三个数，向前比较，插入到已经排序的左侧。
 * 3，再对数组的第4，5...n，
 * 参考https://www.cnblogs.com/coding-996/p/12275710.html
 */
public class S3InsertSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.getSortInput();
        //方法一，插入采用交换发。
        solution(arr);
        ArrayUtil.printArr(arr);
        //方法二，插入采用移动法
        arr = ArrayUtil.getSortInput();
        solution2(arr);
        ArrayUtil.printArr(arr);
    }

    private static void solution2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int temp = arr[j];
            while (j - 1 > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void solution(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j - 1 > 0 && arr[j] < arr[j - 1]) {
                    ArrayUtil.swap(arr, j, j - 1);
                    j--;
            }
        }
    }
}
