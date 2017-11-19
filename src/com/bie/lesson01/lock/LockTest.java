package com.bie.lesson01.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @author  Author:别先生 
* @date Date:2017年11月19日 下午5:11:14 
*
* 1:lock和synchronized的区别
*　　1)Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性。
*		Lock是一个类，通过这个类可以实现同步访问；
*　　2)Lock和synchronized有一点非常大的不同，采用synchronized不需要用户去手动释放锁，
*		当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；
*		而Lock则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。
* 2:java.util.concurrent.locks包下常用的类
* 3:Lock
*　　 首先要说明的就是Lock，通过查看Lock的源码可知，Lock是一个接口：
* 4:Lock接口中每个方法的使用：
*	1)lock()、tryLock()、tryLock(long time, TimeUnit unit)、
*		lockInterruptibly()是用来获取锁的。	
*	2)unLock()方法是用来释放锁的。
* 5:lock()方法是平常使用得最多的一个方法，就是用来获取锁。如果锁已被其他线程获取，则进行等待。
*	由于在前面讲到如果采用Lock，必须主动去释放锁，并且在发生异常时，不会自动释放锁。
*	因此一般来说，使用Lock必须在try{}catch{}块中进行，并且将释放锁的操作放在finally块中
*	进行，以保证锁一定被被释放，防止死锁的发生。
* 6:Lock和synchronized的选择
*　　1)Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
*　    2)synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；
*		而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，
*		因此使用Lock时需要在finally块中释放锁；
*　　3)Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，
*		等待的线程会一直等待下去，不能够响应中断；
*　　4)通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
*　　5)Lock可以提高多个线程进行读操作的效率。
*　　		在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时
*		（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，
*		在具体使用时要根据适当情况选择。
*/
public class LockTest {

	private static List<Integer> list = new ArrayList<>();
	public static Lock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		 new Thread(){
			 public void run(){
				 Thread thread = Thread.currentThread();
				 lock.lock();//获取到锁
				 try {
					System.out.println(thread.getName() + "得到了锁......");
					for(int i=0; i<5; i++){
						list.add(i);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					System.out.println(thread.getName() + "释放了锁.....");
					lock.unlock();//释放了锁，必须手动释放锁
				}
			 }
		 }.start();
		 
		 
		 new Thread(){
			 public void run(){
				 Thread thread = Thread.currentThread();
				 lock.lock();//获取到锁
				 try {
					System.out.println(thread.getName() + "得到了锁......");
					for(int i=0; i<5; i++){
						list.add(i);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					System.out.println(thread.getName() + "释放了锁.....");
					lock.unlock();//释放了锁，必须手动释放锁
				}
			 }
		 }.start();
	}
	
}
