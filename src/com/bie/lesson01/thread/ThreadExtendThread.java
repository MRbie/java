package com.bie.lesson01.thread;

import java.util.Random;

/** 
* @author  Author:������ 
* @date Date:2017��11��19�� ����4:01:43 
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
		//�õ���ǰ�̵߳�����
		String tName = Thread.currentThread().getName();
		System.out.println(tName + " �̵߳�run����������......");
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
		Thread thread1 = new ThreadExtendThread("�߳�һ");
		Thread thread2 = new ThreadExtendThread("�̶߳�");
		
		//�����߳�
		thread1.start();
		thread2.start();
		//����ǵ���thread��run��������ֻ��һ����ͨ�ķ������ã����Ὺ���µ��߳�
		//thread1.run();
		//thread2.run();
	}
	
}

