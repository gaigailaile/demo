package com.gai.stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

public class Stack {
    private Queue<Integer> queue1 = null;
    private Queue<Integer> queue2 = null;

    public Stack(){
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }

    public void push(int node){
        if(this.queue1.isEmpty()){
            this.queue2.offer(node);
        }else {
            this.queue1.offer(node);
        }
    }

    public int pop() throws Exception {
        if(this.queue1.isEmpty() && this.queue2.isEmpty()){
            throw new Exception("栈为空!");
        }
        int res = 0;
        if(this.queue1.isEmpty()){
            while (!this.queue2.isEmpty()){
                res = this.queue2.poll();
                if(!this.queue2.isEmpty()){
                    this.queue1.offer(res);
                }
            }
        }else if(this.queue2.isEmpty()){
            while (!this.queue1.isEmpty()){
                res = this.queue1.poll();
                if(!this.queue1.isEmpty()){
                    this.queue2.offer(res);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        Stack stack = new Stack();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
