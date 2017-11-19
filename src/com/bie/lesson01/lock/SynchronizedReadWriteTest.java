package com.bie.lesson01.lock;
/** 
* @author  Author:别先生 
* @date Date:2017年11月19日 下午8:16:17 
*
* 1:一个线程又要读又要写，用synchronize来实现的话，读写操作都只能锁住后一个线程一个线程地进行.
*/
public class SynchronizedReadWriteTest {

	 public static void main(String[] args)  {
        final SynchronizedReadWriteTest test = new SynchronizedReadWriteTest();
         
        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();
         
        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();
         
    }  
     
    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        int i=0;
        while(System.currentTimeMillis() - start <= 1) {
        	i++;
        	if(i%4==0){
            System.out.println(thread.getName()+"正在进行写操作");
        	}else {
        		System.out.println(thread.getName()+"正在进行读操作");	
			}
        }
        System.out.println(thread.getName()+"读写操作完毕");
    }
}
