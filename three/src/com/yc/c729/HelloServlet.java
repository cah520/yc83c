package com.yc.c729;

import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		
		//�����������
		String name=request.getParameter("name");
		PrintWriter pw=response.getWriter();
		//�����ҳ��
		
		pw.print("<h1>hello"+name+" </h1>");
		System.out.println("hello world");
	}
}
