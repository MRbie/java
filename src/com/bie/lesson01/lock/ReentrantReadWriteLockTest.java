package com.bie.lesson01.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 
* @author  Author:别先生 
* @date Date:2017年11月19日 下午8:07:08 
*
* 
* 1:使用读写锁，可以实现读写分离锁定，读操作并发进行，写操作锁定单个线程
* 2:如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，
* 	则申请写锁的线程会一直等待释放读锁。
* 3:如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，
* 	则申请的线程会一直等待释放写锁。
* 4:ReentrantLock
*	直接使用lock接口的话，我们需要实现很多方法，不太方便，
*	ReentrantLock是唯一实现了Lock接口的类，并且ReentrantLock提供了更多的方法，
*	ReentrantLock，意思是“可重入锁”。
* 5:　ReentrantReadWriteLock里面提供了很多丰富的方法，
* 	不过最主要的有两个方法：readLock()和writeLock()用来获取读锁和写锁。
* 6:注意：
*　　不过要注意的是，如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，
*		则申请写锁的线程会一直等待释放读锁。
*	如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，
*	则申请的线程会一直等待释放写锁。
*/
public class ReentrantReadWriteLockTest {

	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
     
    public static void main(String[] args)  {
        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
         
        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
                test.write(Thread.currentThread());
            };
        }.start();
         
        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
                test.write(Thread.currentThread());
            };
        }.start();
         
    }  
    
    /**
     * 读操作,用读锁来锁定
     * @param thread
     */
    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
             
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }

    /**
     * 写操作，用写锁来锁定
     * @param thread
     */
    public void write(Thread thread) {
        rwl.writeLock().lock();;
        try {
            long start = System.currentTimeMillis();
             
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行写操作");
            }
            System.out.println(thread.getName()+"写操作完毕");
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
