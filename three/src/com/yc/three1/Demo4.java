package com.yc.three1;

import java.util.ArrayList;
import java.util.List;

public class Demo4 {
	
	public static void main(String[] args) {
		Producercomsumer pc=new Producercomsumer();
		new Thread(){
			public void run(){
				try {
					pc.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		new Thread(){
			public void run(){
				try {
					pc.consume();;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
	
}
	
	class Producercomsumer{
		private List<Integer>list=new ArrayList<>();
		
		/*
		 * 生产方法
		 */
		public synchronized void produce() throws InterruptedException{
			while(true){
				if(list.isEmpty()){
					for(int i=0;i<10;i++){
						list.add(i);
						System.out.println("生产了"+i);
						Thread.sleep(200);
					}
				}else{
					//通知等待线程(消费线程)
					notifyAll();
					wait();
				}
			}
		}
		
		/*
		 * 消费方法
		 */
		public synchronized void consume() throws InterruptedException{
			while(true){
				if(list.isEmpty()==false){
					for(int i=0;i<10;i++){
						list.remove(0);
						System.out.println("消费了"+i);
						Thread.sleep(100);
					}
				}else{
					//通知等待线程(生产线程)
					notifyAll();
					wait();
				}
			}
		}
	}

