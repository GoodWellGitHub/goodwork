package com.org.alllock;

import org.testng.annotations.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2018/7/17.
 */
public class ConditionTest {
    private static BoundedBuffer bb = new BoundedBuffer();

    @Test
    public void testcondition() {
        for (int i = 0; i < 10; i++) {
            new PutThread(" p " + i, i).start();
            new TakeThread(" t " + i).start();
            ;
        }
    }

    static class PutThread extends Thread {
        private int num;

        public PutThread(String name, int num) {
            super(name);
            this.num = num;
        }

        public void run() {
            try {
                Thread.sleep(1);
                bb.put(num);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class TakeThread extends Thread {
        public TakeThread(String name) {
            super(name);
        }

        public void run() {
            try {
                Thread.sleep(1);
                Integer num = (Integer) bb.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[5];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            notEmpty.signal();
            System.out.println(Thread.currentThread().getName() + " put " + (Integer) x);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[takeptr];
            if (++takeptr == items.length)
                takeptr = 0;
            --count;
            notFull.signal();
            System.out.println(Thread.currentThread().getName() + " take " + (Integer) x);
            return x;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}