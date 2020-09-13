package com.zhi.zblog.biz;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zhi.zblog.pojo.User;
import com.zhi.zblog.util.BizException;

@SpringBootTest
public class UserBizTest {

	@Resource
	private UserBiz ubiz;
	
	@Test
	public void testLogin() throws BizException {
		User user=new User();
		user.setAccount("zhangsan");
		user.setPwd("123456");
		
		System.out.println(ubiz.login(user));
	}
}
