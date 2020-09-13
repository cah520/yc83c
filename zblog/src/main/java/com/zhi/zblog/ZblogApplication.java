package com.zhi.zblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhi.zblog.dao")
public class ZblogApplication {  

	public static void main(String[] args) {
		SpringApplication.run(ZblogApplication.class, args);
	}

}
    