package com.gai.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static final ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        new Thread(()->test(),"线程A").start();
        new Thread(()->test(),"线程B").start();
        new Thread(()->test(),"线程C").start();
    }

    public static void test(){
        for (int i = 0; i < 2; i++){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获取锁!");
                TimeUnit.SECONDS.sleep(2);
                //执行逻辑
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
//                System.out.println(Thread.currentThread().getName() + "释放锁!");
            }
        }
    }
}
