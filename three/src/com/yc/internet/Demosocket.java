package com.yc.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Demosocket {

	
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(8888);
		
		System.out.println("服务器启动成功,端口8888");
		//接受客户端连接
		//IO操作，导致程序再次阻塞,客户端连接成功后结束阻塞，返回Socket
		Socket socket=server.accept();
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