package com.lts.arth;

/**
 * @author luotianshun
 * @date 2021/2/2
 * @menu
 */
public class SortedTwoArrays {

    /**
     * 两个排序数组长度m和n，获取所有数的上中位数。要求时间复杂度为 O(log (m+n))，
     * 参考 【分步详解】两个有序数组中的中位数和Top K问题 by Vosky
     * 题目来自力扣和牛客网。用到的算法思想：~分治+迭代,(~应该还有分治+递归的情况)
     * 由原博客代码，自己改造的，原博客代码验证有问题。
     * @param args
     */
//  数组上中位数计算位置：
//    1)从位置0开始(length-1)/2；
//    2)位置m和n范围(包含m，n)，(m+n)/2
    public static void main(String args[]) {

        int[] n1 = {3,4,5,8};
        int[] n2 = {1,2,7,9};
//        int[] n1 = {5,6,7,10};
//        int[] n2 = {1,2,3,4};
//        int[] n1 = {1,2,3};
//        int[] n2 = {5,6,7,8};
//        int[] n1 = {5};
//        int[] n2 = {1,6};
//        int[] n1 = {5};
//        int[] n2 = {6};
//        int[] n1 = {};
//        int[] n2 = {6};

        //求两个数组的上中位数,
        double topMedianSortedArrays = findTopMedianSortedArrays(n1, n2);
        System.out.println(topMedianSortedArrays);

        //扩展，求两个数组的中位数,在findTopMedianSortedArrays方法基础上改一下就行
        double medianSortedArrays = findMedianSortedArrays(n1, n2);
        System.out.println(medianSortedArrays);

    }

    /**
     *两个排序数组长度m和n，获取所有数的上中位数.
     * 分治+迭代
     * 主要思想：对较短的数组进行2分，获取位置c1，然后根据(m+n-1)/2 - c1 - 1计算另一个数组分割位置c2.
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return
     */
    public static double findTopMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        //保证数组nums1一定最短
        if(n > m) {
            return findTopMedianSortedArrays(nums2,nums1);
        }
        //考虑最短数组为空的情况。
        if (n == 0) {
            return nums2[(m-1)/2];
        }


        int L1 = 0,L2 = 0,R1,R2,c1,c2,lo = 0, hi = n-1;
        while(true)   //二分
        {
            //c1是二分的位置,把nums1分成两半，当h1==-1的时候，表示这个数组所有元素都大于另一个数组
            //当c1=n-1的时候，表示这个数组所有元素都小于另一个数组
            c1 = hi == -1 ? -1 :(lo+hi)/2;
            c2 = (m+n-1)/2 - c1 - 1; //根据c1计算另一数组分割的位置，把nums2分成两半，c1+c2=(m+n)/2,
            L1 = (c1 == -1) ? Integer.MIN_VALUE:nums1[c1];   //nums1左半边最大值
            R1 = (c1 == n - 1)?Integer.MAX_VALUE:nums1[c1 + 1];  //nums1右半边最大值，考虑右边没元素的话，用Integer.MAX_VALUE
            L2 = (c2 == -1)?Integer.MIN_VALUE:nums2[c2];//nums2左半边最大值
            R2 = (c2 == m - 1)?Integer.MAX_VALUE:nums2[c2 + 1];  //nums2右半边最大值

            if(L1 > R2)
                hi = c1 - 1;//取分割位置的左边，
            else if(L2 > R1)
                lo = c1 + 1;//取分割位置的右边
            else
                break;
        }
        return (Math.max(L1,L2));
    }

    /**
     *两个排序数组长度m和n，获取所有数的中位数.
     * 分治+迭代
     * 主要思想：对较短的数组进行2分，获取位置c1，然后根据(m+n-1)/2 - c1 - 1计算另一个数组分割位置c2.
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        //保证数组nums1一定最短
        if(n > m) {
            return findTopMedianSortedArrays(nums2,nums1);
        }
        //考虑最短数组为空的情况。
        if (n == 0) {
            if ((m + n)%2 != 0) {
                return nums2[(m - 1)/2];
            } else {
                return (nums2[(m - 1)/2] + nums2[(m - 1)/2 + 1])/2.0;
            }
        }


        int L1 = 0,L2 = 0,R1,R2,c1,c2,lo = 0, hi = n-1;
        while(true)   //二分
        {
            //c1是二分的位置,把nums1分成两半，当h1==-1的时候，表示这个数组所有元素都大于另一个数组
            //当c1=n-1的时候，表示这个数组所有元素都小于另一个数组
            c1 = hi == -1 ? -1 :(lo+hi)/2;
            c2 = (m+n-1)/2 - c1 - 1; //根据c1计算另一数组分割的位置，把nums2分成两半，c1+c2=(m+n)/2,
            L1 = (c1 == -1) ? Integer.MIN_VALUE:nums1[c1];   //nums1左半边最大值
            R1 = (c1 == n - 1)?Integer.MAX_VALUE:nums1[c1 + 1];  //nums1右半边最大值，考虑右边没元素的话，用Integer.MAX_VALUE
            L2 = (c2 == -1)?Integer.MIN_VALUE:nums2[c2];//nums2左半边最大值
            R2 = (c2 == m - 1)?Integer.MAX_VALUE:nums2[c2 + 1];  //nums2右半边最大值

            if(L1 > R2)
                hi = c1 - 1;//取分割位置的左边，
            else if(L2 > R1)
                lo = c1 + 1;//取分割位置的右边
            else
                break;
        }
        if ((m + n)%2 != 0) {
            return (Math.max(L1,L2));
        } else {
            return (Math.max(L1,L2) + Math.min(R1,R2)) / 2.0;
        }
    }
}

