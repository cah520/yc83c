package com.yc.three1;

import java.util.Scanner;

public class Demo1 {

	public static void main(String[] args) {
		A a=new A("a�߳�");
		B b=new B();
		Thread t=new Thread(b,"b�߳�");
		//�����߳�
		a.start();
		t.start();
		//��ȡ��ǰ�߳�
		System.out.println("mian getName:"+Thread.currentThread().getName());
		System.out.println("mian getId:"+Thread.currentThread().getId());
		System.out.println("mian getPriority:"+Thread.currentThread().getPriority());
		System.out.println("mian getState:"+Thread.currentThread().getState());
	}
	
	public static void a(){
		Scanner sc=new Scanner(System.in);
		System.out.println("������:");
		String s=sc.nextLine();
		System.out.println("���������:"+s);
	}
	public static void b(){
		System.out.println("����b����");
	}
	//��̬�ڲ���ʵ���߳���
	//�̳�Thread��ʵ�ֶ��߳�
	public static class A extends Thread{
		//��д����Ĺ��췽��
				public A(String name) {
					super(name);
					
				}
		
		public void run() {
			a();
			System.out.println("a getName:"+Thread.currentThread().getName());
			System.out.println("a getId:"+Thread.currentThread().getId());
			System.out.println("a getPriority:"+Thread.currentThread().getPriority());
			System.out.println("a getState:"+Thread.currentThread().getState());
		}
		
	}
	//ʵ��Runnable�ӿ�
	public static class B implements Runnable{
		
		public void run() {
		//�߳�����
			System.out.println("b�����߳�����");
			try {
				Thread.sleep(10*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			b();
			System.out.println("b getName:"+Thread.currentThread().getName());
			System.out.println("b getId:"+Thread.currentThread().getId());
			System.out.println("b getPriority:"+Thread.currentThread().getPriority());
			System.out.println("b  getState:"+Thread.currentThread().getState());
		}
	}
}
