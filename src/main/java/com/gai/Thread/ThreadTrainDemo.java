package com.gai.Thread;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTrainDemo {
    public static void main(String[] args) {
        ThreadTrain threadTrain = new ThreadTrain();
        Thread thread = new Thread(threadTrain);
        Thread thread1 = new Thread(threadTrain);
        thread.start();
        thread1.start();
    }
}

class ThreadTrain implements Runnable{
    private int ticket = 100;
    private Object obj = new Object();
    private ReentrantLock  lock  = new ReentrantLock();
    private ThreadLocal threadLocal = new ThreadLocal();

//    public void run() {
//        while (ticket > 0){
//            String name = Thread.currentThread().getName();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if(ticket > 0){
//                System.out.println(name + ",出售第" + (100-ticket+1) +"张票");
//                ticket--;
//            }
//        }
//    }

    //加锁
//    public void run() {
//        while (ticket > 0){
//            String name = Thread.currentThread().getName();
//            try {
//                Thread.sleep(40);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            synchronized (ThreadTrain.class){
//                if(ticket > 0){
//                    System.out.println(name + ",出售第" + (100-ticket+1) +"张票");
//                    ticket--;
//                }
//            }
//        }
//    }

    //同步方法
//    public void run() {
//        while (ticket > 0){
//            sale();
//        }
//    }
//
//    public synchronized void sale(){
//        String name = Thread.currentThread().getName();
//        if(ticket > 0){
//            try {
//                Thread.sleep(40);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(name + ",出售第" + (100-ticket+1) +"张票");
//            ticket--;
//        }
//    }

    public void run() {
        while (ticket > 0){
//            try {
//                lock.lock();
//                String name = Thread.currentThread().getName();
//                try {
//                    Thread.sleep(40);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if(ticket > 0){
//                    System.out.println(name + ",出售第" + (100-ticket+1) +"张票");
//                    ticket--;
//                }
//            }finally {
//                lock.unlock(); //进行解锁
//            }
            sale();
        }
    }

    public void sale(){
            lock.lock();
            String name = Thread.currentThread().getName();
            if(ticket > 0){
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + ",出售第" + (100-ticket+1) +"张票");
                ticket--;
            }
            lock.unlock();
    }
}
