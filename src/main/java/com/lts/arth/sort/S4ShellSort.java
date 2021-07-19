package com.lts.arth.sort;

/**
 * @author luotianshun
 * @date 2021/7/16
 * @menu 希尔排序
 *在数组中采用跳跃式分组的策略，通过某个增量将数组元素划分为若干组，然后分组进行插入排序，随后逐步缩小增量，继续按组进行插入排序操作，直至增量为1。
 * 希尔排序通过这种策略使得整个数组在初始阶段达到从宏观上看基本有序，小的基本在前，大的基本在后。
 * 然后缩小增量，到增量为1时，其实多数情况下只需微调即可，不会涉及过多的数据移动。
 *
 * 我们来看下希尔排序的基本步骤，在此我们选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，这种增量选择我们可以用一个序列来表示，
 * {n/2,(n/2)/2...1}，称为增量序列。希尔排序的增量序列的选择与证明是个数学难题，
 * 我们选择的这个增量序列是比较常用的，也是希尔建议的增量，称为希尔增量，但其实这个增量序列不是最优的。此处我们做示例使用希尔增量。
 *
 * 参考https://www.cnblogs.com/chengxiao/p/6104371.html，可以配合图解看
 */
public class S4ShellSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.getSortInput();
        solution(arr);
        ArrayUtil.printArr(arr);
    }

    private static void solution(int[] arr) {
        for (int gap = arr.length; gap > 0; gap = gap/2) {//gap=几，相当于数据被分成几组，分别在这几组中做插入排序。
            //注意这里是i=gap，不是gap-1
            //~注意这里的逻辑不是按每一组，来做插入排序，而是先对所有组做第一步，再对所有组做第二步。
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >=0 && arr[j] < arr[j - gap]) {
                    ArrayUtil.swap(arr, j, j - gap);
                    j = j -gap;
                }
            }
        }
    }
}
