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
	//通过socket获取输出流
	private OutputStream out;
	private int status;
	private String msg;
	//存放头域键值对的map集合
	private Map<String,String>headermap=new HashMap<>();
	//保存发送给浏览器的cookie
	private List<Cookie> list=new ArrayList<>();
	
	public HttpServletResponse(OutputStream out){
		this.out=out;
	}
	//资源流，字符串输出流，将输出内容保存到一个字符串中
	private CharArrayWriter caw=new CharArrayWriter();
	//处理流
	private PrintWriter pw=new PrintWriter(caw);
	
	/*获取响应输出流，临时保存Servlet输出的内容
	 * 
	 * 
	 * */
	public PrintWriter getWriter(){
		return pw;
	}
	/*
	 * 设置结果码和结果信息*/
	public void setStatus(int status,String msg){
		//如果已经设置了编码值就不再设置
		if(this.status==0){
			this.status=status;
			this.msg=msg;
		}
		
	}
	/*
	 * 将响应报文推送给浏览器*/
	public void flusBuffer() throws IOException{
		// 响应头行
		out.write(("HTTP/1.1 "+ status + msg + " OK\n").getBytes());
		// 响应头域
		out.write(("Content-Type: text/html; charset=utf-8\n").getBytes());
		//将头域集合中的值写入响应报文
		for(Entry<String, String>entry:headermap.entrySet()){
		out.write((entry.getKey()+":"+entry.getValue()+ "\n").getBytes());
			
		}
		//迭代器循环
		for(Iterator<Cookie> iterator =list.iterator();iterator.hasNext();){
			Cookie cookie=iterator.next();
			out.write(cookie.toString().getBytes());
		}
		// 空行
		out.write("\n".getBytes());
		//实体
		out.write(caw.toString().getBytes());
	}
	/*
	 * 响应重定向方法*/
	public void sendRedirect(String uri){
		//写入结果码
		// 301 or 302
		setStatus(301, "Redirect");
		//在头域中写入Location:要跳转的界面
		headermap.put("Location", uri);
	}
	public void addCookie(Cookie cookie) {
		list.add(cookie);
		
	}
}
