package com.yc.c729;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpServletResponse {
	//ͨ��socket��ȡ�����
	private OutputStream out;
	private int status;
	private String msg;
	//���ͷ���ֵ�Ե�map����
	private Map<String,String>headermap=new HashMap<>();
	//���淢�͸��������cookie
	private List<Cookie> list=new ArrayList<>();
	
	public HttpServletResponse(OutputStream out){
		this.out=out;
	}
	//��Դ�����ַ������������������ݱ��浽һ���ַ�����
	private CharArrayWriter caw=new CharArrayWriter();
	//������
	private PrintWriter pw=new PrintWriter(caw);
	
	/*��ȡ��Ӧ���������ʱ����Servlet���������
	 * 
	 * 
	 * */
	public PrintWriter getWriter(){
		return pw;
	}
	/*
	 * ���ý����ͽ����Ϣ*/
	public void setStatus(int status,String msg){
		//����Ѿ������˱���ֵ�Ͳ�������
		if(this.status==0){
			this.status=status;
			this.msg=msg;
		}
		
	}
	/*
	 * ����Ӧ�������͸������*/
	public void flusBuffer() throws IOException{
		// ��Ӧͷ��
		out.write(("HTTP/1.1 "+ status + msg + " OK\n").getBytes());
		// ��Ӧͷ��
		out.write(("Content-Type: text/html; charset=utf-8\n").getBytes());
		//��ͷ�򼯺��е�ֵд����Ӧ����
		for(Entry<String, String>entry:headermap.entrySet()){
		out.write((entry.getKey()+":"+entry.getValue()+ "\n").getBytes());
			
		}
		//������ѭ��
		for(Iterator<Cookie> iterator =list.iterator();iterator.hasNext();){
			Cookie cookie=iterator.next();
			out.write(cookie.toString().getBytes());
		}
		// ����
		out.write("\n".getBytes());
		//ʵ��
		out.write(caw.toString().getBytes());
	}
	/*
	 * ��Ӧ�ض��򷽷�*/
	public void sendRedirect(String uri){
		//д������
		// 301 or 302
		setStatus(301, "Redirect");
		//��ͷ����д��Location:Ҫ��ת�Ľ���
		headermap.put("Location", uri);
	}
	public void addCookie(Cookie cookie) {
		list.add(cookie);
		
	}
}
