package com.org.threadmethod;

import com.org.thread.ThreadJoin;

/**
 * Created by admin on 2018/7/13.
 */
public class TestJoin {
    public static void main(String[] args){
        //test1();
        test1();
    }
    public static void test1(){
        Thread thread1=new Thread(()->{
            for (int i=0;i<50;i++){
                System.out.println("第一个线程--->"+i);
            }
        }
        );
        Thread thread2=new Thread(()->{
            for (int i=0;i<30;i++){
                System.out.println("第二个线程-->"+i);
            }
        });
        Thread thread3=new Thread(()->{
            for (int i=0;i<20;i++)
                System.out.println("第三个线程-->"+i);
        });
        try {
            thread1.start();
            thread2.start();
            thread2.join();
            thread3.start();
            thread3.join();
            thread1.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void test2(){
        Thread thread1=new Thread(()->{
            for (int i=0;i<50;i++)
            System.out.println("第一个线程-->"+i);
        });
        //thread1.start();
        Thread thread2=new Thread(new ThreadJoin(thread1,"第二个线程"));
        Thread thread3=new Thread(new ThreadJoin(thread2,"第三个线程"));
        Thread thread4=new Thread(new ThreadJoin(thread3,"第四个线程"));
        thread4.start();

    }

}
