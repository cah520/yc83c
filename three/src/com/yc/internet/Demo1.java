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
		
		System.out.println(url.getProtocol());//url中协议

		System.out.println(url.getPort());//url中端口

		System.out.println(url.getPath());//url中资源路径

		System.out.println(url.getHost());//url中域名

		System.out.println(url.getFile());//url中资源名
		System.out.println(url.getQuery());//url中地址参数
		URLConnection conn=url.openConnection();

		System.out.println(conn.getLastModified());//目标资源最后修改时间
		System.out.println(conn.getContentLengthLong());//目标资源大小
		System.out.println(conn.getContentType());//目标资源类型
		System.out.println("=============");
		
		//获取输入流
		InputStream in=conn.getInputStream();
		byte[] buffer=new byte[1024];
		int count;
		while((count=in.read(buffer))>0){
			System.out.println(new String(buffer,0,count));
		}
		in.close();
	}
}
