package com.zhi.zblog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.zhi.zblog.pojo.User;

@Repository
public interface UserMapper {

	@Insert("insert into user values("
			+ "null,#{name},#{account},"
			+ "#{pwd},#{phone},#{email},"
			+ "#{head},now(),#{status},"
			+ "#{type})")
	public int insert(User user);
	
	@Select("select count(*) from user where account=#{account}")
	public int selectByAccount(String account);
	
	@Select("select * from user where account=#{account} and pwd=#{pwd}")
	public User selectByLogin(User user);
}
