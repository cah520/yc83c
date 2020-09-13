package com.yc.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Demo1 {
	public static void main(String[] args) throws IOException {
		
		URL url=new URL("https://www.baidu.com/");
		
		System.out.println(url.getProtocol());//url��Э��

		System.out.println(url.getPort());//url�ж˿�

		System.out.println(url.getPath());//url����Դ·��

		System.out.println(url.getHost());//url������

		System.out.println(url.getFile());//url����Դ��
		System.out.println(url.getQuery());//url�е�ַ����
		URLConnection conn=url.openConnection();

		System.out.println(conn.getLastModified());//Ŀ����Դ����޸�ʱ��
		System.out.println(conn.getContentLengthLong());//Ŀ����Դ��С
		System.out.println(conn.getContentType());//Ŀ����Դ����
		System.out.println("=============");
		
		//��ȡ������
		InputStream in=conn.getInputStream();
		byte[] buffer=new byte[1024];
		int count;
		while((count=in.read(buffer))>0){
			System.out.println(new String(buffer,0,count));
		}
		in.close();
	}
}
