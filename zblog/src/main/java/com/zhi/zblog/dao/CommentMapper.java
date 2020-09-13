package com.zhi.zblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.zhi.zblog.pojo.Comment;

@Repository
public interface CommentMapper {

	@Insert("insert into comment values("
			+ "null,#{articleid},#{content},#{createby},now()"
			+ ")")
	@Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
	public int insert(Comment comm);
	
	@Select("select * from comment where articleid=#{articleid}")
	public List<Comment> selectByArticle(int articleid);
	
	@Select("select * from comment")
	public List<Comment> selectAllArticle();
}
