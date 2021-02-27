package com.gai.interview.linklist;

public class ListNode {
    int data;
    ListNode next;

    public ListNode(int data){
        this.data = data;
    }

    public void print(){
        System.out.print(this.data + " ");
        if(this.next != null){
            this.next.print();
        }
    }
}
