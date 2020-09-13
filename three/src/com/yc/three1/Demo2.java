package com.yc.three1;

public class Demo2 {
	
	public static void main(String[] args) {
		
		
		//匿名内部类
		Thread t1=new Thread("线程1"){
			
			//类定义，匿名方式
			public void run(){
				for(int i=0;i<1000;i++){
					System.out.println(Thread.currentThread().getName()+":"+i);
					
				}
			}
		};
		
Thread t2=new Thread("====线程2"){
			
			//类定义，匿名方式
			public void run(){
				for(int i=0;i<1000;i++){
					System.out.println(Thread.currentThread().getName()+":"+i);
					//t2线程执行到i=500时，join到t1中
					if(i==500){
						try {
							t1.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		t1.start();
		t2.start();
	}
}