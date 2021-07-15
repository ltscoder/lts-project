package com.lts.arth.other;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author luotianshun
 * @date 2021/7/13
 * @menu 链表
 */
public class LinkedListTest {

    /**
     * 单链表反转
     */
    @Test
    public void singleLinkedListReverse() {
        Node head = new Node(new Node(new Node(new Node(new Node(null, 5), 4), 3), 2), 1);

        System.out.println(head);
        Node result = doSingleLinkedListReverse(head);
        System.out.println(result);
        String[] strings = new String[2];

    }

    /**
     * 单链表反转
     *
     * @param head 头节点
     * @by myself
     * @return
     */
    private Node doSingleLinkedListReverse(Node head) {
        Node cur = head;
        Node after = cur.next;
        //释放头节点的next
        cur.next = null;
        while (null != after) {
            Node afterAfter = after.next;
            after.next = cur;
            cur = after;
            after = afterAfter;
        }
        return cur;
    }


    private class Node {

        private Node next;
        private Integer val;

        public Node(Node next, Integer val) {
            this.next = next;
            this.val = val;
        }

        @Override
        public String toString() {
            String res = val + "";
            Node next = this.next;
            while (null != next) {
                res += "->" + next.val;
                next = next.next;
            }
            return res;
        }
    }
}
