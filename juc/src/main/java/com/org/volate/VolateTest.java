package com.org.volate;

import org.testng.annotations.Test;

/**
 * Created by admin on 2018/7/16.
 */
public class VolateTest {
    @Test
    public void test01(String[] args){
        MyThread thread=new MyThread();
        new Thread(thread).start();
        while (true){
            if(thread.isFlag()){
                System.out.println("$$$$$$$$$$$$");
                break;
            }
        }
    }
    @Test
    public void testsycnized(){
        MyThread thread=new MyThread();
        new Thread(thread).start();
        while (true) {
            synchronized (thread) {
                if (thread.isFlag()) {
                    System.out.println("$$$$$$$$$$$$");
                    break;
                }
            }
        }
    }
    @Test
    public void testVolatile(){
        MyThread thread=new MyThread();
        new Thread(thread).start();
        while (true){
            if(thread.isFlag()){
                System.out.println("$$$$$$$$$$$$");
                break;
            }
        }
    }
}




class MyThread implements Runnable{
    private volatile boolean flag=false;
    //private boolean flag=false;
    public void run() {
        try {
            Thread.sleep(200);
        }catch (Exception e){
            e.printStackTrace();
        }
        flag=true;
        System.out.println("flag="+isFlag());
    }
    public boolean isFlag(){
        return flag;
    }
}