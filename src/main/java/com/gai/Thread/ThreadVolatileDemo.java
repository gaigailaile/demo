package com.gai.Thread;

public class ThreadVolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadVolatile threadVolatile = new ThreadVolatile();
        threadVolatile.start();
        Thread.sleep(3000);
        threadVolatile.setRuning(false);
        System.out.println("flag 已经设置成false");
        Thread.sleep(1000);
        System.out.println(threadVolatile.flag);
    }
}

class ThreadVolatile extends Thread {
    public volatile boolean flag = true;
    @Override
    public void run() {
        System.out.println("开始执行子线程....");
        while (flag) {
        }
        System.out.println("线程停止");
    }
    public void setRuning(boolean flag) {
        this.flag = flag;
    }

}
