package com.gai.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptedTest {
    private static final ReentrantLock lockOne = new ReentrantLock();
    private static final ReentrantLock lockTwo = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread = new Thread(new TreadDemo(lockOne,lockTwo),"线程A");
        Thread thread2 = new Thread(new TreadDemo(lockTwo,lockOne),"线程B");
        thread.start();
        thread2.start();
        thread.interrupt();
    }
}

class TreadDemo implements Runnable{
    ReentrantLock lockOne;
    ReentrantLock lockTwo;

    public TreadDemo(ReentrantLock lockOne,ReentrantLock lockTwo){
        this.lockOne = lockOne;
        this.lockTwo = lockTwo;
    }

    @Override
    public void run() {
        try {
            lockOne.lockInterruptibly();
            TimeUnit.SECONDS.sleep(10);
            lockTwo.lockInterruptibly();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lockOne.unlock();
            lockTwo.unlock();
            System.out.println(Thread.currentThread().getName() + "获取资源正常结束!");
        }
    }
}
