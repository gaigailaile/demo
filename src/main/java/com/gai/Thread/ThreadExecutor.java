package com.gai.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor {
//    public static void main(String[] args) {
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
////             try {
////                Thread.sleep(index * 1000);
////             } catch (InterruptedException e) {
////                e.printStackTrace();
////             }
//            cachedThreadPool.execute(new Runnable() {
//                public void run() {
//                    System.out.println(Thread.currentThread().getName() + "---" + index);
//                }
//            });
//        }
////        cachedThreadPool.shutdown();
//    }

//    public static void main(String[] args) {
//        final ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(3);
//
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            newCachedThreadPool.execute(new Runnable() {
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (Exception e) {
//                        // TODO: handle exception
//                    }
//                    System.out.println(Thread.currentThread().getName()+ ":" + index);
//                }
//            });
//        }
//    }

//    public static void main(String[] args) {
//        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(5);
//        newScheduledThreadPool.schedule(new Runnable() {
//            public void run() {
//                System.out.println("delay 3 seconds");
//            }
//        }, 3, TimeUnit.SECONDS);
//    }

    public static void main(String[] args) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            newSingleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+ " :" + index);
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            });
        }
    }
}
