package com.yc.URL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/**
 * 
 * 1.单线程下载
 * 2.
 * @author Administrator
 *
 */
public class Demoxiazai {
	/*当前正在下载块*/
	private int downnums=0;
	public static void main(String[] args) throws IOException, InterruptedException {
		new Demoxiazai().download();
	}
	//定义下载方法
	public void download() throws IOException, InterruptedException{
		URL url=new URL("https://mirrors.bfsu.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip");
		long time=System.currentTimeMillis();
		URLConnection conn=url.openConnection();
		//获取文件总大小
		int filesize=conn.getContentLength();
		//每块的大小（2M）
		int blocksize=2*1024*1024;
		String filename="e:/apache-tomcat-10.0.0-M7-windows-x64.zip";
		//计算块数
		int blocknums=filesize/blocksize;
		if(filesize%blocksize!=0){
			blocknums++;
		}
		
		
		System.out.println("开始下载");
		
		//分块下载
		for(int i=0;i<blocknums;i++){
			downnums++;
			int index=i;//jdk会对其自动加final
			new Thread(){
				public void run(){
					System.out.println("第"+(index+1)+"块开始下载");
					
					/*
					 * 每次循环获取一个连接对象*/
					try {
						 URLConnection conn=url.openConnection();
						InputStream in =conn.getInputStream();
						FileOutputStream out=new FileOutputStream(filename+index);
						//开始的字节数
						int beginbytes=index*blocksize;
						//结束的字节数
						int endbytes=beginbytes+blocksize;
						
						//结束字节数不呢超过文件大小
						if(endbytes>filesize){
							endbytes=filesize;
						}
						
						//跳过开始的字节数
						in.skip(beginbytes);
						
						//当前下载位置
						int position=beginbytes;
						byte[]buffer=new byte[1024];
						int count;
						while((count=in.read(buffer))>0){
							if(position+count>endbytes){
								//超出部分
								int a=position+count-endbytes;
								//减去超出部分
								count=count-a;
							}
							out.write(buffer,0,count);
							//跟新下载位置
							position=position+count;
							//如果下载位置达到该块结束位置
							if(position>=endbytes){
								break;
								
							}
						}
						in.close();
						out.close();
						
						System.out.println("第"+(index+1)+"块结束下载");
						//匿名类访问外部类的对象方式是Demoxiazai.this
						synchronized(Demoxiazai.this){
							Demoxiazai.this.downnums--;
							//通知等待中主线程，尝试完成合并
							Demoxiazai.this.notify();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
			
		}
		
		
		//在此等待
		synchronized(this){
			while(downnums>0){
				wait();
			}
		}
		
		marge(filename,blocknums);
		System.out.println((System.currentTimeMillis()-time)/1000);
		System.out.println("下载完成");
	}
	//合并文件
	public static void marge(String path,int blocknums) throws IOException{
		FileOutputStream out=new FileOutputStream(path);
		for(int i=0;i<blocknums;i++){
			FileInputStream out2=new FileInputStream(path+i);
			byte[]buffer=new byte[1024];
			int count;
			while((count=out2.read(buffer))>0){
				out.write(buffer,0,count);
			}
			out2.close();
		}
		out.close();
}
}