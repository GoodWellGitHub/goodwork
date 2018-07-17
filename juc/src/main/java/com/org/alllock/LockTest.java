package com.org.alllock;

import org.testng.annotations.Test;

import javax.swing.plaf.TableHeaderUI;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2018/7/16.
 */
public class LockTest {
    @Test
    public void testWaitnotify(){
        ThreadA ta=new ThreadA("ta");
        synchronized (ta){
            try {
                System.out.println(Thread.currentThread().getName()+" start ta");
                ta.start();
                System.out.println(Thread.currentThread().getName()+" block");
                ta.wait();
                System.out.println(Thread.currentThread().getName()+" continue");
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    }

    private static Lock lock=new ReentrantLock();
    private static Condition condition=lock.newCondition();
    @Test
    public void testlock(){
        ThreadLock threadLock=new ThreadLock("taa");
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" start taa");
            threadLock.start();
            System.out.println(ThreadLock.currentThread().getName()+" block");
            condition.await();
            System.out.println("已经阻塞");
            System.out.println(Thread.currentThread().getName()+" continue");
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            lock.unlock();
            System.out.println(ThreadLock.currentThread().getName()+"释放锁");
        }

    }
    class ThreadLock extends Thread {
        public ThreadLock(String name) {
            super(name);
        }
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("准备进入锁");
            lock.lock();
            System.out.println("进入锁");
            try {
                System.out.println(Thread.currentThread().getName()+" wakup others");
                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println(ThreadLock.currentThread().getName()+"释放锁");

            }
        }
    }

}
class ThreadA extends Thread {
    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " wakup others");
            notify();
        }
    }
}

