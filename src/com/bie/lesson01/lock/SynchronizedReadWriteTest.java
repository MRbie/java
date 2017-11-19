package com.bie.lesson01.lock;
/** 
* @author  Author:������ 
* @date Date:2017��11��19�� ����8:16:17 
*
* 1:һ���߳���Ҫ����Ҫд����synchronize��ʵ�ֵĻ�����д������ֻ����ס��һ���߳�һ���̵߳ؽ���.
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
            System.out.println(thread.getName()+"���ڽ���д����");
        	}else {
        		System.out.println(thread.getName()+"���ڽ��ж�����");	
			}
        }
        System.out.println(thread.getName()+"��д�������");
    }
}
