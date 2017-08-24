package main;

public class ThreadQuesiton {	
	//private volatile static boolean ready =true;
	private   static boolean flag =true;
	private static class  ReaderThread extends Thread{
		public void run(){
			while(flag);
				//问题1 ： 即使主线程设置flag=false;这里却一直获得已经失效的值true，不退出循环。
				//问题2 ：在不使用volatile同步时，使用打印后却不会再获得失效值，不使用打印就一直获得失效值
				//System.out.print("");
			
			System.out.println("Thread Death.");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ReaderThread().start();
		Thread.currentThread().sleep(1000);
		flag = false;
	}

}

