package com.yc.three1;

import java.util.Scanner;

public class Demo1 {

	public static void main(String[] args) {
		A a=new A("a线程");
		B b=new B();
		Thread t=new Thread(b,"b线程");
		//启动线程
		a.start();
		t.start();
		//获取当前线程
		System.out.println("mian getName:"+Thread.currentThread().getName());
		System.out.println("mian getId:"+Thread.currentThread().getId());
		System.out.println("mian getPriority:"+Thread.currentThread().getPriority());
		System.out.println("mian getState:"+Thread.currentThread().getState());
	}
	
	public static void a(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入:");
		String s=sc.nextLine();
		System.out.println("你输入的是:"+s);
	}
	public static void b(){
		System.out.println("这是b方法");
	}
	//静态内部类实现线程类
	//继承Thread类实现多线程
	public static class A extends Thread{
		//重写父类的构造方法
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
	//实现Runnable接口
	public static class B implements Runnable{
		
		public void run() {
		//线程休眠
			System.out.println("b方法线程休眠");
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
