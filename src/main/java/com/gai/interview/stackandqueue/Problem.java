package com.gai.interview.stackandqueue;

import java.util.*;
import java.util.Queue;
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

    /*
    *   如何得到一个数据流中的中位数？
    *   如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
    *   如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
    *
    * */
    public static void problem5(){
        class MedianFinder{
            Queue<Integer> min,max;

            public MedianFinder(){
                //存放大值 值都比min中的大
                max = new PriorityQueue<>();
                //存放小值
                min = new PriorityQueue<>((v1,v2) -> v2-v1);
            }

            public void addNum(int num){
                if(min.size() != max.size()){
                    max.add(num);
                    min.add(max.poll());
                }else {
                    min.add(num);
                    max.add(min.poll());
                }
            }

            public double findMedian(){
                return max.size() != min.size() ? max.peek() : (max.peek() + min.peek())/2.0;
            }
        }
    }

    /*
    *   问题6 I 滑动窗口的最大值
    *
    *   给定一个数组nums 和滑动窗口的大小k，请找出所有滑动窗口里的最大值。
    * */
    public static int[] problem6(){
        return null;
    }

    /*
     *   问题6 II 队列的最大值
     *
     *   请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
     *   若队列为空，pop_front 和 max_value 需要返回 -1
     * */
    class MaxQueue {
        Queue<Integer> q;
        Deque<Integer> d;

        public MaxQueue() {
            q = new LinkedList<Integer>();
            d = new LinkedList<Integer>();
        }

        public int max_value() {
            if (d.isEmpty()) {
                return -1;
            }
            return d.peekFirst();
        }

        public void push_back(int value) {
            while (!d.isEmpty() && d.peekLast() < value) {
                d.pollLast();
            }
            d.offerLast(value);
            q.offer(value);
        }

        public int pop_front() {
            if (q.isEmpty()) {
                return -1;
            }
            int ans = q.poll();
            if (ans == d.peekFirst()) {
                d.pollFirst();
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        problem5();
    }
}


