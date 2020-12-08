package com.gai.thread;

public class ThreadMain {
    public static void main(String[] args) {
        ExtendsThread extendsThread = new ExtendsThread();
        extendsThread.setName("线程2");
        extendsThread.start();

//        Thread thread = new Thread(new RunnableThread());
//        thread.start();

//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//                for (int i=0;i<20;i++){
//                    System.out.println("Thread run i:" + i);
//                }
//            }
//        });
//
//        thread.start();
    }
}

class ExtendsThread extends Thread{
    @Override
    public void run() {
        for (int i=0;i<20;i++){
            System.out.println("Thread run i:" + getName() + "|" + getId()+ "|" + i);
        }
    }
}

class RunnableThread implements Runnable{
    public void run() {
        Thread.currentThread().getName();
        Thread.currentThread().getId();
        for (int i=0;i<20;i++){
            System.out.println("Thread run i:" + i);
        }
    }
}
