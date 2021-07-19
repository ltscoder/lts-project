package com.lts.arth.sort;

/**
 * @author luotianshun
 * @date 2021/7/16
 * @menu 归并排序
 * 好像还有多路归并，不懂略
 *
 * 参考https://www.cnblogs.com/chengxiao/p/6194356.html
 */
public class S7MergeSort {
    public static void main(String[] args) {
        int[] arr = ArrayUtil.getSortInput();
        solution(arr);
        ArrayUtil.printArr(arr);
    }

    private static void solution(int[] arr) {
        sort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    private static void sort(int[] arr, int low, int high, int[] temp) {
        if (low >= high) {
            return;
        }
        int mid = (low + high)/2;
        sort(arr, low, mid, temp);
        sort(arr, mid + 1, high, temp);
        merge(arr, low, mid, high, temp);
        String s = "";
    }

    private static void merge(int[] arr, int low, int mid, int high, int[] temp) {
        int i = low;
        int j = mid + 1;
        int t = 0;
        while (i <= mid || j <= high) {
            if (i > mid) {
                temp[t++] = arr[j++];
            } else if (j > high) {
                temp[t++] = arr[i++];
            } else if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        t = 0;
        int k = low;
        while (k <= high) {
            arr[k++] = temp[t++];
        }
        //注意这里之前写的时候用for循环，k++了两次，导致错误。
//        for (int k = low; k <= high; k++) {
//            arr[k++] = temp[t++];
//        }
    }
}
