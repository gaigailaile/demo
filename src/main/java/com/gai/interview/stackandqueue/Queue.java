package com.gai.interview.stackandqueue;

import java.util.Stack;

public class Queue {
    private Stack<Integer> stack1 = new Stack();
    private Stack<Integer> stack2 = new Stack();

    public void push(int node){
        this.stack1.push(node);
    }

    public int pop() throws Exception {
        if(this.stack1.isEmpty() && this.stack2.isEmpty()){
            throw new Exception("队列为空");
        }
        if(this.stack2.isEmpty()){
            while (!this.stack1.isEmpty()){
                this.stack2.push(this.stack1.pop());
            }
        }
        return this.stack2.pop();
    }

    public static void main(String[] args) throws Exception {
        Queue queue = new Queue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        int a = queue.pop();
        System.out.println(a);
    }
}
