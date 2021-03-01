package com.gai.interview.stackandqueue;

import java.util.Stack;

public class Problem {

    /*
    *   定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
    *   在该栈中，调用min、push及pop的时间复杂度都是O(1).
    * */
    public static void problem3(){
        class Stack3{
            Stack<Integer> stack1 = new Stack<>();
            Stack<Integer> stack2 = new Stack<>();

            public void push(int node){
                stack1.push(node);
                if(stack2.isEmpty()){
                    stack2.push(node);
                }else {
                    if(stack2.peek() >= node){
                        stack2.push(node);
                    }
                }
            }

            public void pop(){
                if(stack1.pop() == stack2.peek()){
                    stack2.pop();
                }
            }

            public int min(){
                return stack2.peek();
            }
        }
        Stack3 stack3 = new Stack3();
        stack3.push(4);
        stack3.push(3);
        stack3.push(3);
        stack3.push(2);
        stack3.push(1);
        System.out.println(stack3.min());
        stack3.pop();
        System.out.println(stack3.min());
        stack3.pop();
        System.out.println(stack3.min());
        stack3.pop();
        System.out.println(stack3.min());
    }

    /*
    *   输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
    *   例如序列 1,2,3,4,5是某栈的压入顺序.
    *   序列 4,5,3,2,1 是该压栈序列对应的一个弹出序列.
    *   但4,3,5,1,2 就不可能是该压栈序列的弹出序列.（注意：这两个序列的长度是相等的）
    * */
    public static boolean problem4(int[] push,int[] pop){
        if((push == null || pop == null)
                || push.length != pop.length){
            return false;
        }
        Stack<Integer> temp = new Stack<>();
        int index = 0;
        for(int i = 0; i < push.length; i++){
            temp.push(push[i]);
            while (!temp.isEmpty() && temp.peek() == pop[index]){
                temp.pop();
                index++;
            }
        }
        return temp.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(problem4(new int[]{1,2,3,4,5},new int[]{4,5,3,2,1}));
    }
}


