package com.lts.arth.sort;

/**
 * @author luotianshun
 * @date 2021/7/19
 * @menu 基数排序
 * ~，是基于计数排序的，依次对个位，十位，百位...进行计数排序，于是整个数组就有序了
 * 参考https://www.cnblogs.com/skywang12345/p/3603669.html
 */
public class S9RadixSort {

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214, 154, 63, 616};
        solution(arr);
        ArrayUtil.printArr(arr);
    }

    private static void solution(int[] arr) {
        int exp;    // 指数。当对数组按各位进行排序时，exp=1；按十位进行排序时，exp=10；...
        int max = getMax(arr);    // 数组a中的最大值

        // 从个位开始，对数组a按"指数"进行排序
        for (exp = 1; max / exp > 0; exp *= 10)
            //计数排序
            countSort(arr, exp);
    }

    /**
     * 计数排序
     *
     * @param arr 排序数组
     * @param exp 指数，用于确定对哪个位(个位，十位，百位)进行排序。
     */
    private static void countSort(int[] arr, int exp) {
        //int output[arr.length];    // 存储"被排序数据"的临时数组
        int[] output = new int[arr.length];    // 存储"被排序数据"的临时数组
        int[] buckets = new int[10];

        // 将数据出现的次数存储在buckets[]中
        for (int i = 0; i < arr.length; i++)
            buckets[(arr[i] / exp) % 10]++;

        // 更改buckets[i]。目的是让更改后的buckets[i]的值，是该数据在output[]中的位置。
        for (int i = 1; i < 10; i++)
            buckets[i] += buckets[i - 1];

        // 将数据存储到临时数组output[]中
        for (int i = arr.length - 1; i >= 0; i--) {
            output[buckets[(arr[i] / exp) % 10] - 1] = arr[i];
            buckets[(arr[i] / exp) % 10]--;
        }

        // 将排序好的数据赋值给a[]
        for (int i = 0; i < arr.length; i++)
            arr[i] = output[i];
    }

    private static int getMax(int[] arr) {
        int max;

        max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }
}
