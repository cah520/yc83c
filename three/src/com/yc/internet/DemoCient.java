package com.yc.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class DemoCient {
	public static void main(String[] args) throws IOException {
		
		//ֻҪnew��Socket���ͻ������������
		Socket socket=new Socket("127.0.0.1",8888);
		
		
		
		//�ҵĵ�ַ
		InetAddress myaddress	=socket.getInetAddress();
		SocketAddress otheraddress=socket.getRemoteSocketAddress();
		System.out.println("�ҵĵ�ַ"+myaddress);
		System.out.println("�ͻ��˵�ַ"+otheraddress);
		
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		Scanner sc=new Scanner(System.in);
		
		new Thread(){
			public void run(){
				byte[]bt=new byte[1024];
				int count;
				while(true){
					try {
						//û���յ���Ϣ��һֱ����
						count=in.read(bt);
						System.out.println("��˵:"+new String(bt,0,count));
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
				}
			}
		}.start();
		
			
		new Thread(){
			public void run(){
				
				while(true){
					try {
						System.out.println("��˵:");
						out.write(sc.nextLine().getBytes());
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
				}
			}
		}.start();
			
		
}
	
}
