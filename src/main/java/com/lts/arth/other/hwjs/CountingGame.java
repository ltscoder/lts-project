package com.lts.arth.other.hwjs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luotianshun
 * @date 2021/7/14
 * @menu 报数游戏
 * 100个人围成一圈，每个人有一个编码，编号从1开始到100。
 * 他们从1开始依次报数，报到为M的人自动退出圈圈，然后下一个人接着从1开始报数，直到剩余的人数小于M。请问最后剩余的人在原先的编号为多少？
 * 例如输入M=3时，输出为： “58,91” ，输入M=4时，输出为： “34,45,97”。
 */
public class CountingGame {


    public static void main(String[] args) {
        int inputM = 4;
        //方法1 通过双环路链表。
        //~迭代
        String result = solution(inputM);
        System.out.println(result);

        //方法2，通过队列。~相当于环路链表
        //方法2更简单
        //~迭代
        result = solution2(inputM);
        System.out.println(result);
    }

    /**
     * by me
     * @param inputM
     * @return
     */
    private static String solution2(int inputM) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        int count = 1;
        while (list.size() >= inputM) {
            Integer poll = list.poll();
            if (count == inputM) {
                count = 1;
            } else {
                count++;//下一个计数count++,当前计数为count
                list.add(poll);
            }
        }
        return list.stream().sorted().map(String::valueOf).collect(Collectors.joining(","));
    }

    /**
     * by me
     * @param inputM
     * @return
     */
    private static String solution(int inputM) {

        //构建一个环链表
        Node head = new Node(1);
        Node next = head;
        for (int i = 2; i <= 100; i++) {
            next.next = new Node(i);
            next.next.prev = next;
            next = next.next;
        }
        next.next = head;
        head.prev = next;

        //
        int removed = 0;
        Node cur = head;
        int count = 1;
        while (removed <= 100 - inputM) {
            if (count == inputM) {
                count = 1; //下一个计数为1
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
                removed++;
            } else {
                count++; //下一个计数count++,当前计数为count
            }
            cur = cur.next;
        }
        return removed != 100 ? cur.toString() : "";
    }

    private static class Node {

        int val;
        Node next;
        Node prev;

        public Node(int data) {
            this.val = data;
        }

        @Override
        public String toString() {
            List<Integer> res = new ArrayList<>();
            res.add(val);
            Node next = this.next;
            while (null != next && next != this) {
                res.add(next.val);
                next = next.next;
            }
            return res.stream().sorted().map(String::valueOf).collect(Collectors.joining(","));
        }
    }
}
