package com.org.alllock;

import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by admin on 2018/7/16.
 */
public class CountDownLatchTest {
    final CountDownLatch latch = new CountDownLatch(10);

    @Test
    public void testCount() {
        LatchThread latchThread = new LatchThread(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(latchThread).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间： " + (end - start));
    }

}

class LatchThread implements Runnable {
    private CountDownLatch latch;

    public LatchThread(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        synchronized (this) {
            try {

            } catch (Exception e) {
                for (int i = 0; i < 5000; i++) {
                    if (i % 2 == 0)
                        System.out.println(i);
                }
            } finally {
                latch.countDown();
            }
        }
    }
}