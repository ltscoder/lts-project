package com.lts.arth.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/7/19
 * @menu 桶排序
 * 参考https://blog.csdn.net/qq_27124771/article/details/87651495
 * 桶排序是计数排序的扩展版本，计数排序可以看成每个桶只存储相同元素，
 * 而桶排序每个桶存储一定范围的元素，通过映射函数，将待排序数组中的元素映射到各个对应的桶中，
 * 对每个桶中的元素进行排序，最后将非空桶中的元素逐个放入原序列中。
 * 桶排序需要尽量保证元素分散均匀，否则当所有数据集中在同一个桶中时，桶排序失效。
 * ~算是分治思想吧
 */
public class S10bucketSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.getSortInput();
        solution(arr);
        ArrayUtil.printArr(arr);
        
    }

    private static void solution(int[] arr) {

        //计算最大值和最小值
        int max = 0;
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        //计算桶的数量
        int bucketNum = (max - min) /arr.length + 1;
        List<List<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<>());
        }
        //将元素放入桶中
        for (int i = 0; i < arr.length; i++) {
            bucketArr.get((arr[i] - min) / arr.length).add(arr[i]);
        }
        //对每个桶排序
        for (int i = 0; i < bucketArr.size(); i++) {
            //桶排序的方式，可以采用任意其它排序方法
            Collections.sort(bucketArr.get(i));
        }
        //将桶中的元素赋值到原数组
        int index = 0;
        for (int i = 0; i < bucketArr.size(); i++) {
            for (int j = 0; j < bucketArr.get(i).size(); j++) {
                arr[index++] = bucketArr.get(i).get(j);
            }
        }
    }
}
