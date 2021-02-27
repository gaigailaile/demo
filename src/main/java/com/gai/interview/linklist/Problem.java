package com.gai.interview.linklist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem {

    /*
    *   输入一个链表，从尾到头打印链表每个节点的值。
    *
    *   方案一 利用栈的特性来做
    *
    *   方案二 使用递归实现
    * */
    public static List<Object> problem1(Node node,int i){
        List<Object> list = new ArrayList<>();
        Stack<Node> stack = new Stack();
        //存到栈里
        while (node != null){
            stack.push(node);
            node = node.next;
        }

        //读取栈的数据
        while (!stack.isEmpty()){
            list.add(stack.pop().data);
        }

        return list;
    }

    public static List<Object> problem1(Node node){
        List<Object> list = new ArrayList<>();

        if(node != null){
            if(node.next != null){
                list = problem1(node.next);
            }
            list.add(node.data);
        }

        return list;
    }

    /*
    *   给定单链表头指针和一个结点指针，定义一个函数在O(1)时间内删除该结点。
    * */
    public static void problem2(Node head,Node deleteNode){
        if(head == null || deleteNode == null){
            return;
        }
        if(deleteNode.next != null){
            Node next = deleteNode.next;
            deleteNode.data = next.data;
            deleteNode.next = next.next;
        }else {
            if(head == deleteNode){
                head = null;
            } else {
                Node current = head;
                while (current.next != deleteNode)
                    current = current.next;
                current.next = null;
            }
        }
    }

    /*
    *   在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
    * */
    public static ListNode problem3(ListNode head){
        if(head == null){
            return null;
        }

        //记录原链表的相关信息
        ListNode result = new ListNode(-1);
        result.next = head;
        //记录当前节点
        ListNode current = head;
        //记录上一个节点,初始化为result可以处理头部节点重复的问题
        ListNode pre = result;

        while (current != null && current.next != null){
            if(current.data == current.next.data){
                int value = current.data;
                while (current != null && current.data == value){
                    current = current.next;
                }
                pre.next = current;
            }else {
                pre = current;
                current = current.next;
            }
        }

        return result.next;
    }

    /*
    *   输入一个链表，输出该链表中倒数第 k 个结点。
    * */
    public static ListNode problem4(ListNode head,int k){
        if(head == null || k < 1){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;

        while (k-- > 1) {
            if(fast.next == null){
                return null;
            }
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /*
    *   一个链表中包含环，请找出该链表的环的入口结点。
    * */
    public static ListNode problem5(ListNode head){
        if(head == null || head.next == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            //两个指针一个一次走两步，一个一次走一步
            slow = slow.next;
            fast = fast.next.next;

            //当它们相遇时说明快指针比慢的多走了一个环的距离
            if(slow == fast){
                //将快指针拨回到head节点 一次走一步，当它们在次相约时就是环的入口
                fast = head;
                while (fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                if(slow == fast){
                    return slow;
                }
            }
        }
        return null;
    }

    /*
     *  问题6
     *  输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     *
     *  递归
     * */
    public static ListNode problem6(ListNode node1,ListNode node2){
        //当两个节点有null时,非空的大
        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }
        //比较两个节点大小,将小的节点返回。大的节点继续和小的下一个节点比较大小
        if(node1.data < node2.data){
            node1.next = problem6(node1.next,node2);
            return node1;
        }else {
            node2.next = problem6(node1,node2.next);
            return node2;
        }
    }

    /*
     *  问题6
     *
     *  循环迭代
     * */
    public static ListNode problem6(ListNode node1,ListNode node2,int a){
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;

        while (node1 != null && node2 != null){
            if(node1.data < node2.data){
                pre.next = node1;
                node1 = node1.next;
            }else {
                pre.next = node2;
                node2 = node2.next;
            }
            pre = pre.next;
        }

        pre.next = node1 == null ? node2:node1;

        return preHead.next;
    }

    public static void main(String[] args) {
        ListNode node1_1= new ListNode(1);
        ListNode node1_3 = new ListNode(3);
        ListNode node1_5 = new ListNode(5);
        ListNode node1_7 = new ListNode(7);
        node1_1.next = node1_3;
        node1_3.next = node1_5;
        node1_5.next = node1_7;

        ListNode node2_2 = new ListNode(2);
        ListNode node2_4 = new ListNode(4);
        ListNode node2_6 = new ListNode(6);
        ListNode node2_8 = new ListNode(8);
        ListNode node2_9 = new ListNode(9);
        node2_2.next = node2_4;
        node2_4.next = node2_6;
        node2_6.next = node2_8;
        node2_8.next = node2_9;

        ListNode result = problem6(node1_1,node2_2,1);
        result.print();
    }
}
