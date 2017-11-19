package com.bie.lesson01.thread;
/** 
* @author  Author:别先生 
* @date Date:2017年11月19日 下午4:33:03 
*
* 1:synchronized的缺陷
*	synchronized是java中的一个关键字，也就是说是Java语言内置的特性。
*	如果一个代码块被synchronized修饰了，当一个线程获取了对应的锁，并执行该代码块时，
*		其他线程便只能一直等待，等待获取锁的线程释放锁，而这里获取锁的线程释放锁只会有两种情况：
*			1)获取锁的线程执行完了该代码块，然后线程释放对锁的占有;
*			2)线程执行发生异常，此时JVM会让线程自动释放锁;
*
*
*/
public class SynchronizedTest {

	public static void main(String[] args) {
		final SynchronizedTest syn = new SynchronizedTest();
		final SynchronizedTest syn2 = new SynchronizedTest();
		new Thread("线程一"){
			public void run(){
				synchronized (syn) {
					try {
						System.out.println(this.getName() + "start......");
						//int i = 1/0;//如果发生异常，jvm会将锁释放
						Thread.sleep(5000);
						System.out.println(this.getName() + "醒了......");
						System.out.println(this.getName() + "结束了......");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		}.start();;
		
		System.out.println("======================================");
		
		new Thread("线程二"){
			public void run(){
				//争抢同一把锁时，线程一没释放之前，线程2只能等待
				synchronized (syn) {
					//synchronized (syn2) {	//如果不是一把锁。可以看到两句话同时打印
					System.out.println(this.getName() + "开始了......");
					System.out.println(this.getName() + "结束了......");
				}
			}
		}.start();
	}
	
	
}
