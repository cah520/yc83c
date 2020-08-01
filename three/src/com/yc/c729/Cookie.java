package com.yc.c729;

public class Cookie {

	
	private String name;
	private String value;
	private int maxAge=-1;
	public Cookie(String name,String value){
		this.name=name;
		this.value=value;
	}
	
	public String toString(){
		String s="Set-cookie: %s=%s;\n";
		s=String.format(s, name,value);
		
		if(maxAge>-1){
			s+="Max-Age="+maxAge+";";
		}
		s+="\n";
		System.out.println("s="+s);
		return s;
	}
	
	public void setMaxAge(int maxAge){
		this.maxAge=maxAge;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public int getMaxAge() {
		System.out.println("=========");
		return maxAge;
	}
	
}
