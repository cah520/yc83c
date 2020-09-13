package com.zhi.zblog.biz;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.zhi.zblog.dao.UserMapper;
import com.zhi.zblog.pojo.User;
import com.zhi.zblog.util.BizException;

@Service
public class UserBiz {

	@Resource
	private UserMapper umapper;
	
	/**
	 * 用户注册
	 * 
	 * Errors 保存所有的报错信息，默认会被推送到页面
	 * @param user
	 * @throws BizException
	 */
	public void register(User user) throws BizException {
		//参数验证在实体类中进行
		//同名验证
		if(umapper.selectByAccount(user.getAccount())>0) {
			throw new BizException("该用户名已存在");
		}
		umapper.insert(user);    
	}
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 * @throws BizException 
	 */
	public User login(User user) throws BizException {
	
		User ruser=umapper.selectByLogin(user);
		
		if(ruser==null) {
			throw new BizException("用户名或密码不正确");
		}
		
		return ruser;
		
	}
}
