package com.yc.three1;

public class Demo2 {
	
	public static void main(String[] args) {
		
		
		//�����ڲ���
		Thread t1=new Thread("�߳�1"){
			
			//�ඨ�壬������ʽ
			public void run(){
				for(int i=0;i<1000;i++){
					System.out.println(Thread.currentThread().getName()+":"+i);
					
				}
			}
		};
		
Thread t2=new Thread("====�߳�2"){
			
			//�ඨ�壬������ʽ
			public void run(){
				for(int i=0;i<1000;i++){
					System.out.println(Thread.currentThread().getName()+":"+i);
					//t2�߳�ִ�е�i=500ʱ��join��t1��
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