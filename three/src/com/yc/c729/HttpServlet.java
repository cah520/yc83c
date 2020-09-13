package com.yc.c729;

import java.io.IOException;

public class HttpServlet implements Servlet {

	@Override
	public void Service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if("GET".equals(request.getMethod())){
			doGet(request,response);
		}else if("POST".equals(request.getMethod())){
			doPost(request,response);
		}else if("PUT".equals(request.getMethod())){
			doPut(request,response);
		}else if("DELETE".equals(request.getMethod())){
			doDelete(request,response);
		}else{
			//其他doxxx方法
		}
		response.setStatus(200, "OK");
		response.flusBuffer();
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
