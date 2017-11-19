package com.bie.lesson01.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @author  Author:������ 
* @date Date:2017��11��19�� ����5:24:45 
* 
* 1:�۲�����һ���̻߳��������һ���߳�ȡ������������һֱ�ȴ�
* 2:tryLock()�������з���ֵ�ģ�����ʾ�������Ի�ȡ���������ȡ�ɹ����򷵻�true��
* 	�����ȡʧ�ܣ������ѱ������̻߳�ȡ�����򷵻�false��Ҳ��˵�������������ζ����������ء�
* 	���ò�����ʱ����һֱ���ǵȴ���
*/
public class TryLockTest {

	private static List<Integer> list = new ArrayList<Integer>();
	static Lock lock = new ReentrantLock(); // ע������ط�
	public static void main(String[] args) {
		
		new Thread() {
			public void run() {
				Thread thread = Thread.currentThread();
				boolean tryLock = lock.tryLock();
				System.out.println(thread.getName()+" "+tryLock);
				if (tryLock) {
					try {
						System.out.println(thread.getName() + "�õ�����......");
						for (int i = 0; i < 5; i++) {
							list.add(i);
						}
					} catch (Exception e) {
					} finally {
						System.out.println(thread.getName() + "�ͷ�����......");
						lock.unlock();
					}
				}
			};
		}.start();

		new Thread() {
			public void run() {
				Thread thread = Thread.currentThread();
				boolean tryLock = lock.tryLock();
				System.out.println(thread.getName()+" "+tryLock);
				if (tryLock) {
					try {
						System.out.println(thread.getName() + "�õ�����......");
						for (int i = 0; i < 5; i++) {
							list.add(i);
						}
					} catch (Exception e) {
					} finally {
						System.out.println(thread.getName() + "�ͷ�����......");
						lock.unlock();
					}
				}

			};
		}.start();
	}
}
