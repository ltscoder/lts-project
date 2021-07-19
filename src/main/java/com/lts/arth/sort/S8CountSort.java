package com.lts.arth.sort;

/**
 * @author luotianshun
 * @date 2021/7/19
 * @menu 计数排序
 * 参考 https://blog.csdn.net/liyuming0000/article/details/46913357
 */
public class S8CountSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.getSortInput();

        //版本1
        solution(arr);
        ArrayUtil.printArr(arr);
        //版本2，空间上作了一点优化
        solution1(arr);
        ArrayUtil.printArr(arr);
    }

    private static void solution(int[] arr) {
        //获取最大最小值，
        int min = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[min];
                continue;
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //创建用于计数的数组
        int[] c = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            c[arr[i] - min]++;  //-min，~标准化，计数从0开始
        }
        for (int i = 1; i < arr.length; i++) {
            c[i] = c[i] + c[i - 1];
        }
        int[] b = new int[arr.length];
        //从arr的最后一个数开始，计算排名，并放到b中对应位置，
        for (int i = arr.length - 1; i >= 0; i--) {
            b[c[arr[i] - min] - 1] = arr[i];//-1，排名-1=序号。
            c[arr[i] - min]--; //--，下一个相同元素的排名要小一位。
        }
        //本来是return b的。这里从b拷贝到arr
        for (int i = 0; i < arr.length; i++) {
            arr[i] = b[i];
        }
//        return b;
    }

    private static void solution1(int[] arr) {
        //获取最大最小值，
        int min = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[min];
                continue;
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //创建用于计数的数组
        int[] c = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            c[arr[i] - min]++;  //-min，~标准化，计数从0开始
        }
        //对计数的结果遍历，将排序结果保存到arr中，
        int a = 0;
        for (int i = 0; i < c.length; i++) {
            for (int count = 0; count < c[i]; count++)  //元素计数了几次，输出几次。
                arr[a++] = i + min;
        }
    }
}
