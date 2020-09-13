package com.yc.c729;

import javax.xml.ws.Response;

public class CookieServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response){

		Cookie cookie=new Cookie("name","zhangsan");
		response.addCookie(cookie);
		response.getWriter().append("cookie");
}
	
}
