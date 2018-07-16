package com.org.atomic;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2018/7/16.
 */
public class AtomicDemo {

    @Test
    public void testcommon(){
        CommonThread commonThread=new CommonThread();
        for (int i=0;i<10;i++){
            new Thread(commonThread).start();
        }
    }
    @Test
    public void testAtomic(){
        CommonThread atomicInteger=new CommonThread();
        for (int i=0;i<10;i++){
            new Thread(atomicInteger).start();
        }
    }
}
class CommonThread implements Runnable{
    private int seriaNumber=0;
    public void run() {
        try{
            Thread.sleep(5);
        }catch (InterruptedException e){
            e.fillInStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" : "+getSeriaNumber());
    }
    public int getSeriaNumber(){
        return seriaNumber++;
    }
}

class AtomicThread implements Runnable{

    private AtomicInteger atomicInteger=new AtomicInteger();
    public void run() {
        try{
            Thread.sleep(5);
        }catch (InterruptedException e){
            e.fillInStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" : "+getSeriaNumber());
    }
    public int getSeriaNumber(){
        return atomicInteger.getAndIncrement();
    }
}