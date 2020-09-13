package com.yc.dao;

import org.apache.ibatis.annotations.Insert;

import com.yc.favorite.bean.Tag;
import com.yc.favorite.bean.TagFavorite;

public interface TagFavoriteMpper2 {
	@Insert("insert into tagfavorite values(#{tid},#{fid})")
	int insert(TagFavorite t);
}
