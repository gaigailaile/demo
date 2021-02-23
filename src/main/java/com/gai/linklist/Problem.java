package com.gai.linklist;

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

    public static void main(String[] args) {
        Node node1 = new Node("A");
        Node node2 = new Node("B");
        Node node3 = new Node("C");
        node1.next = node2;
        node2.next = node3;

        List<Object> list = Problem.problem1(node1);
        for (Object s:list) {
            System.out.print(s.toString() + " ");
        }
    }
}
