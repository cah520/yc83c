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
		 * ��������
		 */
		public synchronized void produce() throws InterruptedException{
			while(true){
				if(list.isEmpty()){
					for(int i=0;i<10;i++){
						list.add(i);
						System.out.println("������"+i);
						Thread.sleep(200);
					}
				}else{
					//֪ͨ�ȴ��߳�(�����߳�)
					notifyAll();
					wait();
				}
			}
		}
		
		/*
		 * ���ѷ���
		 */
		public synchronized void consume() throws InterruptedException{
			while(true){
				if(list.isEmpty()==false){
					for(int i=0;i<10;i++){
						list.remove(0);
						System.out.println("������"+i);
						Thread.sleep(100);
					}
				}else{
					//֪ͨ�ȴ��߳�(�����߳�)
					notifyAll();
					wait();
				}
			}
		}
	}

