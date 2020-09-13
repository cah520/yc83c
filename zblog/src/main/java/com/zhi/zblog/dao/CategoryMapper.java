package com.zhi.zblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.zhi.zblog.pojo.Category;

@Repository
public interface CategoryMapper {

	@Select("select * from category")
	public List<Category> selectAll();

	@Select("select * from category where id=#{id}")
	public Category selectById(int id);
}
