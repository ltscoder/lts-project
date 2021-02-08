package com.lts.arth.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotianshun
 * @date 2021/2/5
 * @menu 路径总和II leecode 113
 */
public class PathSumII {

    /**
     * 路径总和II
     * leecode
     * 定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径
     * 用到的算法思想：搜索算法：dfs,回溯？
     */
    public static void main(String[] args) {
        //构造树
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(3);
        treeNode.left.right.left = new TreeNode(1);
        treeNode.left.right.right = new TreeNode(3);
        treeNode.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(6);
        //目标和
        int sum = 10;

        List<List<Integer>> result = findAll(treeNode, sum);
        System.out.println(result);

    }

    private static List<List<Integer>> findAll(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        if (null == root) {
            return lists;
        }
        List<Integer> path = new ArrayList<>();
        doFindAll(root, sum, lists, path);
        return lists;
    }

    /**
     *
     * @param root 头节点
     * @param sumRemain 目标和减去父级各节点后剩余
     * @param lists 结果集
     * @param path 遍历路径对应的data
     */
    private static void doFindAll(TreeNode root, int sumRemain, List<List<Integer>> lists, List<Integer> path) {
        if (null == root) {
            return;
        }
        //判断是否叶子节点
        if (null == root.left && null == root.right) {
            //sumRemain==root.data,则加入结果集。
            if (sumRemain == root.data) {
                path.add(root.data);
                lists.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
        } else {
            //不是叶子节点，则对子节点dfs。
            //先将当前节点data加入path
            path.add(root.data);
            doFindAll(root.left, sumRemain - root.data, lists, path);
            doFindAll(root.right, sumRemain - root.data, lists, path);
            //将当前节点data从path移除。
            path.remove(path.size() - 1);
        }
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
