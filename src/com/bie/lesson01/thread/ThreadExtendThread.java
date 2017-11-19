package com.bie.lesson01.thread;

import java.util.Random;

/** 
* @author  Author:别先生 
* @date Date:2017年11月19日 下午4:01:43 
*
*
*/
public class ThreadExtendThread extends Thread{

	private String flag;
	public ThreadExtendThread(String flag) {
		this.flag = flag;
	}
	
	@Override
	public void run() {
		//得到当前线程的名称
		String tName = Thread.currentThread().getName();
		System.out.println(tName + " 线程的run方法被调用......");
		Random random = new Random();
		for(int i=0; i<20; i++){
			try {
				Thread.sleep(random.nextInt(10) * 100);
				System.out.println(tName + "......" + flag);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Thread thread1 = new ThreadExtendThread("线程一");
		Thread thread2 = new ThreadExtendThread("线程二");
		
		//启动线程
		thread1.start();
		thread2.start();
		//如果是调用thread的run方法，则只是一个普通的方法调用，不会开启新的线程
		//thread1.run();
		//thread2.run();
	}
	
}

