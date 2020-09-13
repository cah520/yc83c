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
 * 1.���߳�����
 * 2.
 * @author Administrator
 *
 */
public class Demoxiazai {
	/*��ǰ�������ؿ�*/
	private int downnums=0;
	public static void main(String[] args) throws IOException, InterruptedException {
		new Demoxiazai().download();
	}
	//�������ط���
	public void download() throws IOException, InterruptedException{
		URL url=new URL("https://mirrors.bfsu.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip");
		long time=System.currentTimeMillis();
		URLConnection conn=url.openConnection();
		//��ȡ�ļ��ܴ�С
		int filesize=conn.getContentLength();
		//ÿ��Ĵ�С��2M��
		int blocksize=2*1024*1024;
		String filename="e:/apache-tomcat-10.0.0-M7-windows-x64.zip";
		//�������
		int blocknums=filesize/blocksize;
		if(filesize%blocksize!=0){
			blocknums++;
		}
		
		
		System.out.println("��ʼ����");
		
		//�ֿ�����
		for(int i=0;i<blocknums;i++){
			downnums++;
			int index=i;//jdk������Զ���final
			new Thread(){
				public void run(){
					System.out.println("��"+(index+1)+"�鿪ʼ����");
					
					/*
					 * ÿ��ѭ����ȡһ�����Ӷ���*/
					try {
						 URLConnection conn=url.openConnection();
						InputStream in =conn.getInputStream();
						FileOutputStream out=new FileOutputStream(filename+index);
						//��ʼ���ֽ���
						int beginbytes=index*blocksize;
						//�������ֽ���
						int endbytes=beginbytes+blocksize;
						
						//�����ֽ������س����ļ���С
						if(endbytes>filesize){
							endbytes=filesize;
						}
						
						//������ʼ���ֽ���
						in.skip(beginbytes);
						
						//��ǰ����λ��
						int position=beginbytes;
						byte[]buffer=new byte[1024];
						int count;
						while((count=in.read(buffer))>0){
							if(position+count>endbytes){
								//��������
								int a=position+count-endbytes;
								//��ȥ��������
								count=count-a;
							}
							out.write(buffer,0,count);
							//��������λ��
							position=position+count;
							//�������λ�ôﵽ�ÿ����λ��
							if(position>=endbytes){
								break;
								
							}
						}
						in.close();
						out.close();
						
						System.out.println("��"+(index+1)+"���������");
						//����������ⲿ��Ķ���ʽ��Demoxiazai.this
						synchronized(Demoxiazai.this){
							Demoxiazai.this.downnums--;
							//֪ͨ�ȴ������̣߳�������ɺϲ�
							Demoxiazai.this.notify();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
			
		}
		
		
		//�ڴ˵ȴ�
		synchronized(this){
			while(downnums>0){
				wait();
			}
		}
		
		marge(filename,blocknums);
		System.out.println((System.currentTimeMillis()-time)/1000);
		System.out.println("�������");
	}
	//�ϲ��ļ�
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