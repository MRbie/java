package com.bie.lesson01.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @author  Author:������ 
* @date Date:2017��11��19�� ����5:35:07 
* 
*��1:lockInterruptibly()�����Ƚ�����:
*	1)��ͨ���������ȥ��ȡ��ʱ������߳����ڵȴ���ȡ����
*		������߳��ܹ���Ӧ�жϣ����ж��̵߳ĵȴ�״̬��Ҳ��ʹ˵���������߳�ͬʱ
*		ͨ��lock.lockInterruptibly()���ȡĳ����ʱ��������ʱ�߳�A��ȡ��������
*		���߳�Bֻ�еȴ�����ô���߳�B����threadB.interrupt()�����ܹ��ж��߳�B�ĵȴ����̡�
*����2)ע�⣬��һ���̻߳�ȡ����֮���ǲ��ᱻinterrupt()�����жϵġ�
*����	3)��˵�ͨ��lockInterruptibly()������ȡĳ����ʱ��������ܻ�ȡ����ֻ�н��еȴ�������£��ǿ�����Ӧ�жϵġ�
*��	4)����synchronized���εĻ�����һ���̴߳��ڵȴ�ĳ������״̬�����޷����жϵģ�ֻ��һֱ�ȴ���ȥ��
* 2:�۲��������thread-0�õ�����������������thread-1���Ի�ȡ��������ò���������Ա��жϵȴ�
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
    	 //ע�⣬�����Ҫ��ȷ�жϵȴ������̣߳����뽫��ȡ���������棬Ȼ��InterruptedException�׳�
        lock.lockInterruptibly();  
        try {  
            System.out.println(thread.getName()+"�õ�����");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //��������
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"ִ��finally");
            lock.unlock();
            System.out.println(thread.getName()+"�ͷ�����");
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
            System.out.println(Thread.currentThread().getName()+"���ж�");
        }
    }
}
