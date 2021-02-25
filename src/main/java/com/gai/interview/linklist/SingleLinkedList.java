package com.gai.interview.linklist;

public class SingleLinkedList {
    private int size;
    private Node head;

    public SingleLinkedList(){
        this.size = 0;
        this.head = null;
    }

    public void add(Node node){
        if(size == 0){
            this.head = node;
        }else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public Object deleteHead(){
        Object obj = this.head.data;
        this.head = head.next;
        this.size--;
        return obj;
    }

    public Node find(Object obj){
        Node currentNode = this.head;
        int index = this.size;
        while (index > 0){
            if(obj.equals(currentNode.data)){
                return currentNode;
            }else {
                currentNode = currentNode.next;
            }
            index--;
        }
        return null;
    }

    public boolean delete(Object obj){
        if(this.size == 0 || obj == null){
            return false;
        }
        //存放当前节点
        Node currentNode = this.head;
        //存放上一个节点
        Node previousNode = null;
        while (!obj.equals(currentNode.data)){
            if(currentNode.next==null){
                return false;
            }else {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        if(previousNode == null){
            this.head = currentNode.next;
        }else {
            previousNode.next = currentNode.next;
        }
        this.size--;
        return true;
    }

    public void display(){
        if(size > 0){
            Node node = this.head;
            int index = this.size;
            System.out.print("[");
            while (index > 0){
                if(node.next == null){
                    System.out.print(node.data);
                }else {
                    System.out.print(node.data + "->");
                }
                node = node.next;
                index--;
            }
            System.out.println("]");
        }else {
            System.out.println("[]");
        }
    }

    public static void main(String[] args) {
        SingleLinkedList singleList = new SingleLinkedList();
        singleList.add(new Node("A"));
        singleList.add(new Node("B"));
        singleList.add(new Node("C"));
        singleList.add(new Node("D"));

        singleList.display();
        singleList.delete("C");
        singleList.display();
        System.out.println(singleList.find("B"));
    }
}
