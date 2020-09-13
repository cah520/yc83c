package com.yc.three1;

import java.util.ArrayList;
import java.util.List;

public class Demo3 {
	
	//������ֵļ���
	private List<String> list=new ArrayList<String>();
	
	//ͬ������
	public synchronized void add(String i){
		list.add(i);
	}
	
	public String pop(){
		//ͬ�������
		synchronized(this){
			return list.remove(0);
		}
	}
	public synchronized int size(){
		return list.size();
	}
	
	public void test(){
		System.out.println(list);
	}
	
	public static void main(String[] args) {
		Demo3 d=new Demo3();
		
		Thread t=new Thread("�߳�1"){
			public void run(){
			while(true){
				if(d.size()<10){
					for(int i=0;i<10;i++){
						System.out.println(Thread.currentThread()+":"+i);
						d.add(""+i);
					}
				}
			}
			}
		};
		Thread t2=new Thread("�߳�2"){
			public void run(){
				while(true){
					if(d.size()>0){
						System.out.println(Thread.currentThread()+":"+d.pop());
					}
				}
			}
		};
		
		Thread t3=new Thread("�߳�3"){
			public void run(){
				while(true){
					if(d.size()>0){
						System.out.println(Thread.currentThread()+":"+d.pop());
					}
				}
			}
		};
		
		t.start();
		t2.start();
		t3.start();
	}
}
