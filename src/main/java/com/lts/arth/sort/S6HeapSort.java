package com.lts.arth.sort;

/**
 * @author luotianshun
 * @date 2021/7/16
 * @menu 堆排序
 *
 * 大顶堆：完全二叉树，每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆。
 *
 *~：二叉树：
 *~：对于满树，第k层元素个数2的K-1次方。下层元素个数=上面所有元素个数+1=2倍的上一层元素个数
 *~：树从上到下，从左到右分别标上序号0，1，2...,则，其中父节点k和子左节点，子右节点的关系是2k+1,2k+2。
 *~：分析，因为对于一个满二叉树：
 *      下层元素个数=上面所有元素个数+1
 *    --》下层最后元素序号-上层最后元素序号=上层最后元素序号+1+1
 *    --》下层最后元素序号=2*上层最后元素序号+2
 *    --》即=2k+2。
 *    --》当上层指向父节点的指针左移n时，下层对应指向子节点的指针左移2n，所以关系不变。
 * 参考 https://www.cnblogs.com/chengxiao/p/6129630.html
 */
public class S6HeapSort {
    public static void main(String[] args) {
        int[] arr = ArrayUtil.getSortInput();
        solution(arr);
        ArrayUtil.printArr(arr);
    }

    private static void solution(int[] arr) {
        //1构建大顶堆
        //~： 设父节点k，当arr.length - 1 = 2k + 1时，k=arr.length/2 - 1，此时length是一个偶数
        //             当arr.length - 1 = 2k + 2时，k=arr.length/2 - 3/2，此时length是一个奇数，此时可得k=arr.length/2 - 1
        for (int i = arr.length/2 - 1; i >= 0; i--) {
            //从第一个非叶子节点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            ArrayUtil.swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }

    }

    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        //从i结点的左子结点开始，也就是2i+1处开始
        for(int k=i*2+1;k<length;k=k*2+1){  //k=k*2+1，~递归下一级。以保证这次操作后还是大顶堆。
            if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }
}
