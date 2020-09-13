package com.yc.c729;

import java.io.IOException;

public interface Servlet {
	public void Service(HttpServletRequest request,HttpServletResponse response) throws IOException;
}
