package com.bie.lesson01.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @author  Author:别先生 
* @date Date:2017年11月19日 下午5:35:07 
* 
*　1:lockInterruptibly()方法比较特殊:
*	1)当通过这个方法去获取锁时，如果线程正在等待获取锁，
*		则这个线程能够响应中断，即中断线程的等待状态。也就使说，当两个线程同时
*		通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，
*		而线程B只有等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
*　　2)注意，当一个线程获取了锁之后，是不会被interrupt()方法中断的。
*　　	3)因此当通过lockInterruptibly()方法获取某个锁时，如果不能获取到，只有进行等待的情况下，是可以响应中断的。
*　	4)而用synchronized修饰的话，当一个线程处于等待某个锁的状态，是无法被中断的，只有一直等待下去。
* 2:观察现象：如果thread-0得到了锁，阻塞。。。thread-1尝试获取锁，如果拿不到，则可以被中断等待
*/
public class InterruptiblyTest {

	private Lock lock = new ReentrantLock();  
	 
    public static void main(String[] args)  {
    	InterruptiblyTest test = new InterruptiblyTest();
        MyThread thread0 = new MyThread(test);
        MyThread thread1 = new MyThread(test);
        thread0.start();
        thread1.start();
         
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
        System.out.println("=====================");
    }  
     
    public void insert(Thread thread) throws InterruptedException{
    	 //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        lock.lockInterruptibly();  
        try {  
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }  
    }
}
 
class MyThread extends Thread {
    private InterruptiblyTest test = null;
    public MyThread(InterruptiblyTest test) {
        this.test = test;
    }
    @Override
    public void run() {
         
        try {
            test.insert(Thread.currentThread());
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}
