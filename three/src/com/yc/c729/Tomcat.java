package com.yc.c729;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Tomcat {
//服务器初始化servlet容器
	private Map<String,Servlet> servletMap=new HashMap<>(); 
	
public void startup() throws IOException{
	
	servletMap.put("/photo/hello", new HelloServlet());
	servletMap.put("/", new ToIndexServlet());
	servletMap.put("/Cookie", new CookieServlet());
	ServerSocket tomcat = new ServerSocket(8080);

	System.out.println("服务器启动完成,端口8080");
	boolean a = true;
	while (a) {
		// 接受请求

		Socket socket = tomcat.accept();

	

		new Thread() {
			public void run() {
				try {
					System.out.println("收到请求");
					InputStream in = socket.getInputStream();
					OutputStream out = socket.getOutputStream();
					byte[] buffer = new byte[1024];
					int count;
					count = in.read(buffer);
					if(count>0){
					//打印请求报文
					String requesttext=new String(buffer, 0, count);
					System.out.println(requesttext);
					
					//解析请求报文
					HttpServletRequest request=BuildRequest(requesttext);
					HttpServletResponse response=new HttpServletResponse(out);
					//使用资源地址区分静态请求和动态请求
					//使用资源地址到Servlet容器中获取servlet对象
					String uri=request.getRequestURI();
		
					Servlet servlet=servletMap.get(uri);
					if(servlet!=null){
						//使用Servlet处理动态请求
						servlet.Service(request, response);
					}else{
						//如果没有Sevlet对象则为静态请求
						//处理静态请求
						processStaticRequest(request, out);
						
						/*String webpath=request.getRequestURI();
						String contentType;
						//结果码
						
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
							contentType="text/html; charset=utf-8";//图片可以返回html,浏览器可以自动识别
						}
						// 响应头行
						out.write(("HTTP/1.1 "+ statusCode +" OK\n").getBytes());
						// 响应头域
						out.write(("Content-Type: "+ contentType + "\n").getBytes());
						// 空行
						out.write("\n".getBytes());
						// 实体

						//out.write("<h1>hello world<h1>".getBytes());
						
						
						FileInputStream out2=new FileInputStream(path);
						
						while((count=out2.read(buffer))>0){
							out.write(buffer,0,count);
						}
						out2.close();*/
						}	
					
					}
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			
		}.start();
	}
	//代码不可达,所以在while中加入a变量
	tomcat.close();
}

public void shutdown(){
	
}

private HttpServletRequest BuildRequest(String requesttext) {
	
	return new HttpServletRequest(requesttext);
}
public static void main(String[] args) throws IOException {
	new Tomcat().startup();
}

public void processStaticRequest(HttpServletRequest request, OutputStream out) throws IOException{
	String webpath=request.getRequestURI();
	String contentType;
	//结果码
	
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
		contentType="text/html; charset=utf-8";//图片可以返回html,浏览器可以自动识别
	}
	// 响应头行
	out.write(("HTTP/1.1 "+ statusCode +" OK\n").getBytes());
	// 响应头域
	out.write(("Content-Type: "+ contentType + "\n").getBytes());
	// 空行
	out.write("\n".getBytes());
	// 实体

	//out.write("<h1>hello world<h1>".getBytes());
	
	
	FileInputStream out2=new FileInputStream(path);
	byte[]buffer=new byte[1024];
	int count;
	while((count=out2.read(buffer))>0){
		out.write(buffer,0,count);
	}
	out2.close();
}
}
