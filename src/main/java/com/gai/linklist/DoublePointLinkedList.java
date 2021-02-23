package com.gai.linklist;

public class DoublePointLinkedList {
    private int size;
    private Node head;
    private Node tail;

    public DoublePointLinkedList(){
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void addHead(Node node){
        if(size == 0){
            this.head = node;
            this.tail = node;
        }else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public void addTail(Node node){
        if(size == 0){
            this.tail = node;
            this.head = node;
        }else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void add(Node node){
        this.addHead(node);
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
        DoublePointLinkedList doublePointLinkedList = new DoublePointLinkedList();
        doublePointLinkedList.add(new Node("A"));
        doublePointLinkedList.add(new Node("B"));
        doublePointLinkedList.add(new Node("C"));
        doublePointLinkedList.add(new Node("D"));

        doublePointLinkedList.display();
        doublePointLinkedList.delete("C");
        doublePointLinkedList.display();

        doublePointLinkedList.addTail(new Node("E"));
        doublePointLinkedList.display();
        System.out.println(doublePointLinkedList.find("B"));
    }
}
