package com.yc.c729;

import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		
		//请求参数解析
		String name=request.getParameter("name");
		PrintWriter pw=response.getWriter();
		//输出到页面
		
		pw.print("<h1>hello"+name+" </h1>");
		System.out.println("hello world");
	}
}
