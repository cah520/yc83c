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
		
		//只要new出Socket，就会与服务器连接
		Socket socket=new Socket("127.0.0.1",8888);
		
		
		
		//我的地址
		InetAddress myaddress	=socket.getInetAddress();
		SocketAddress otheraddress=socket.getRemoteSocketAddress();
		System.out.println("我的地址"+myaddress);
		System.out.println("客户端地址"+otheraddress);
		
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		Scanner sc=new Scanner(System.in);
		
		new Thread(){
			public void run(){
				byte[]bt=new byte[1024];
				int count;
				while(true){
					try {
						//没有收到消息会一直阻塞
						count=in.read(bt);
						System.out.println("他说:"+new String(bt,0,count));
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
						System.out.println("我说:");
						out.write(sc.nextLine().getBytes());
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
				}
			}
		}.start();
			
		
}
	
}
