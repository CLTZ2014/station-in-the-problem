package main;

/*
 * 同步策略：私有锁
 * 通过一个共享变量的资源同步实现锁功能
 * 私有锁
 * 封装private的私有锁使用public方法访问该锁，可以使其他客户新增的同步代码可以参与到此同步策略中。
 * */

public class PrivateLock {
	//私有锁，通过一个对像的同步来实现保护另一个对象的同步。
	public static final Object  lock = new Object();
	public static void main(String[] args) throws InterruptedException {
		StringBuilder builder = new StringBuilder();
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			Thread thread = new AnotherThread(PrivateLock.lock,builder);
			threads[i] = thread;
			thread.start();
		}
		Thread.currentThread().sleep(1000);
		System.out.println(builder.toString());
	}
}
class AnotherThread extends Thread{
	
	private  Object lock;
	private StringBuilder builder;
	
	public void run(){
		for (int i = 0; i < 100; i++) {
			//同步此lock保证builder的操作是原子性的
			synchronized (lock) {
				builder.append("xx");
			}
		}
	}

	public AnotherThread(Object lock, StringBuilder builder) {
		super();
		this.lock = lock;
		this.builder = builder;
	}
}