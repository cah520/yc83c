package com.yc.c729;

import java.util.HashMap;
import java.util.Map;

public class HttpServletRequest {

	private String method;
	private String requestUri;
	private String protocol;
	//���ͷ���ֵ�Եļ���
	Map<String,String>headermap=new HashMap<>();
	//��Ų����ļ���
	Map<String,String>paramsmap=new HashMap<>();
	
	public HttpServletRequest(String requesttext){
		//��ɶԱ��ĵĽ���
		String[]lines=requesttext.split("\\n");//���з�
		String[] lines2=lines[0].split("\\s");//�ո�
		method=lines2[0];
		requestUri=lines2[1];
		protocol=lines2[2];
		
		int index=lines2[1].indexOf("?");
		if(index!=-1){
			//��������
			requestUri=lines2[1].substring(0,index);
			String paramsString=lines2[1].substring(index+1);
			String []params=paramsString.split("&");
			for(int i=0;i<params.length;i++){
				String[]nv=params[i].split("=");
				if(nv.length==1){
					paramsmap.put(nv[0], "");
				}else if(nv.length>1){
					paramsmap.put(nv[0], nv[1]);
				}
			}
		}
		
		for(int i=1;i<lines.length;i++){
			lines[i]=lines[i].trim();
			if(lines[i].isEmpty()){
				break;
			}
			
			lines2=lines[i].split(":");

			headermap.put(lines2[0], lines2[1].trim());
		}
	}
	//��ȡ����
	public String getMethod(){
		return method;
	}

	public String getRequestURI() {
		
		return requestUri;
	}
	
	public String getProcotol(){
		return protocol;
	}
	
	public String getHeader(String name){
		return headermap.get(name);
	}
	public String getParameter(String name){
		return paramsmap.get(name);
	}
}
