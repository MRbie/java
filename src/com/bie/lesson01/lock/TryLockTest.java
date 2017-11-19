package com.bie.lesson01.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @author  Author:别先生 
* @date Date:2017年11月19日 下午5:24:45 
* 
* 1:观察现象：一个线程获得锁后，另一个线程取不到锁，不会一直等待
* 2:tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，
* 	如果获取失败（即锁已被其他线程获取），则返回false，也就说这个方法无论如何都会立即返回。
* 	在拿不到锁时不会一直在那等待。
*/
public class TryLockTest {

	private static List<Integer> list = new ArrayList<Integer>();
	static Lock lock = new ReentrantLock(); // 注意这个地方
	public static void main(String[] args) {
		
		new Thread() {
			public void run() {
				Thread thread = Thread.currentThread();
				boolean tryLock = lock.tryLock();
				System.out.println(thread.getName()+" "+tryLock);
				if (tryLock) {
					try {
						System.out.println(thread.getName() + "得到了锁......");
						for (int i = 0; i < 5; i++) {
							list.add(i);
						}
					} catch (Exception e) {
					} finally {
						System.out.println(thread.getName() + "释放了锁......");
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
						System.out.println(thread.getName() + "得到了锁......");
						for (int i = 0; i < 5; i++) {
							list.add(i);
						}
					} catch (Exception e) {
					} finally {
						System.out.println(thread.getName() + "释放了锁......");
						lock.unlock();
					}
				}

			};
		}.start();
	}
}
