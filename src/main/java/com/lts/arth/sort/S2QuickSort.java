package com.lts.arth.sort;

/**
 * @author luotianshun
 * @date 2021/7/16
 * @menu 快速排序
 */
public class S2QuickSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.getSortInput();
        solution(arr);
        ArrayUtil.printArr(arr);
    }

    private static void solution(int[] arr) {
        
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序
     * @param arr 数组
     * @param left 低位index
     * @param right 高位index
     */
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int temp = arr[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && arr[j] >= temp) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && arr[i] <= temp) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = temp; // 基准元素就位
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
}
