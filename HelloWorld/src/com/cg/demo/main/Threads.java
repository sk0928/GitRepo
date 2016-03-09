package com.cg.demo.main;

public class Threads/* extends Thread */implements Runnable {
 static int i;
	public void run(){
		for(; i<=100;i++){
			System.out.println(i);
		}
	}
	
	/*public void run(Thread t1){
		run();
		for(int i = 0; i<=20;i++){
			System.out.println(i);
		}
		}*/
	public void start(){
		for(;i<=100;i++){
			System.out.println(i);
		}
	}
	/*public void start(Thread t1){
		run(t1);
		for(int i= 0;i<=20;i++){
			System.out.println(i);
		}
	}*/
}
