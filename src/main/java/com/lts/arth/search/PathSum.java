package com.lts.arth.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author luotianshun
 * @date 2021/2/5
 * @menu 路径总和 leecode 112
 */
public class PathSum {

    /**
     * 路径总和 leecode
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * 用到的算法思想：搜索算法：dfs/bfs
     */
    public static void main(String[] args) {
        //给一个头节点
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(6);
        int sum = 10;
        //~方法1：dfs->递归
        boolean b = dfsHasPathSum(treeNode, sum);
        //方法2：bfs
        boolean b1 = bfsHasPathSum(treeNode, sum);
        System.out.println(b);
        System.out.println(b1);

        //测试2
        sum = 11;
        b = dfsHasPathSum(treeNode, sum);
        b1 = bfsHasPathSum(treeNode, sum);
        System.out.println(b);
        System.out.println(b1);
    }

    private static boolean bfsHasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        Queue<TreeNode> nodes = new LinkedList();
        Queue<Integer> values = new LinkedList();

        nodes.add(root);
        values.add(root.data);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            Integer value = values.poll();

            if (null == node.left && null == node.right) {
                if (sum == value) {
                    return true;
                }
            }
            if (null != node.left) {
                nodes.add(node.left);
                values.add(value + node.left.data);
            }
            if (null != node.right) {
                nodes.add(node.right);
                values.add(value + node.right.data);
            }
        }
        return false;
    }


    /**
     * 我们从根结点开始递归遍历二叉树，用目标和减去遍历经过的节点值，直到节点的左右子树为空(遍历结束)的时候，看目标和是否为0
     * @param root 根节点
     * @param sum 目标总和
     * @return
     */
    public static boolean dfsHasPathSum(TreeNode root, int sum)
    {
        if (root == null)
            return false;

        int flag = sum - root.data;

        if (root.left == null && root.right == null)
            return flag == 0 ? true : false;

        return (dfsHasPathSum(root.left, flag) || dfsHasPathSum(root.right, flag));
    }

    /**
     * 树节点
     */
    public static class TreeNode {
        int data; //值
        TreeNode left;
        TreeNode right;
        TreeNode(int val)
        {
            data = val;
        }
    }
}
