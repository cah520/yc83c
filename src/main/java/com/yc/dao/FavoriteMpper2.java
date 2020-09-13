package com.yc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.bean.Tag;

public interface FavoriteMpper2 {
	@Insert("insert into favorite values(null,#{flabel},"
			+ "#{furl},#{fdesc},#{ftags})")
	@Options(useGeneratedKeys = true,keyColumn = "fid",keyProperty = "fid")
	int insert(Favorite t);
	
	List<Favorite> selectByTid(@Param("tid")Integer tid);
}
