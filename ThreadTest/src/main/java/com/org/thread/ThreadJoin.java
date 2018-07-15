package com.org.thread;

/**
 * Created by admin on 2018/7/13.
 */
public class ThreadJoin implements Runnable{

    public Thread thread;
    public ThreadJoin(Thread thread,String name){
        this.thread=thread;
        Thread.currentThread().setName(name);
    }
    @Override
    public void run() {
        try {
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (int i=0;i<50;i++){
            System.out.println(Thread.currentThread().getName()+"-->"+i);
        }
    }
}
