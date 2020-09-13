package com.yc.c727;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {
	public static void main(String[] args) throws IOException {

		ServerSocket tomcat = new ServerSocket(8080);

		System.out.println("�������������,�˿�8080");
		boolean a = true;
		while (a) {
			// ��������

			Socket socket = tomcat.accept();

		

			new Thread() {
				public void run() {
					try {
						System.out.println("�յ�����");
						InputStream in = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
						byte[] buffer = new byte[1024];
						int count;
						count = in.read(buffer);
						if(count>0){
							//��ӡ������
						String requesttext=new String(buffer, 0, count);
						System.out.println(requesttext);
						//������Դ·��
						String[]lines=requesttext.split("\\n");//���з�
						String[] lines2=lines[0].split("\\s");//�ո�
						String webpath=lines2[1];
						String contentType;
						//�����
						
						int statusCode=200;
						String path="E:/111/"+webpath;
					
						File file=new File(path);
						if(!file.exists()){
							statusCode=404;
							path="E:/111/photo/404.html";
							
						}
						if(webpath.endsWith(".js")){
							contentType="application/javascript; charset=utf-8";
						}else if(webpath.endsWith(".css")){
							contentType="text/css; charset=utf-8";
						}else{
							contentType="text/html; charset=utf-8";//ͼƬ���Է���html,����������Զ�ʶ��
						}
						// ��Ӧͷ��
						out.write(("HTTP/1.1 "+ statusCode +" OK\n").getBytes());
						// ��Ӧͷ��
						out.write(("Content-Type: "+ contentType + "\n").getBytes());
						// ����
						out.write("\n".getBytes());
						// ʵ��

						//out.write("<h1>hello world<h1>".getBytes());
						
						
						FileInputStream out2=new FileInputStream(path);
						
						while((count=out2.read(buffer))>0){
							out.write(buffer,0,count);
						}
						out2.close();
						}
						socket.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}.start();
		}
		//���벻�ɴ�,������while�м���a����
		tomcat.close();

	}
}
