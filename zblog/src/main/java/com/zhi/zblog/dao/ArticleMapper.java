package com.zhi.zblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.zhi.zblog.pojo.Article;

@Repository
public interface ArticleMapper {

	/**
	 * 查询最新文章
	 * 根据文章创建时间降序排序
	 * @return
	 */
	@Select("select * from article order by createtime desc")
	//<ResultMap>==@Results
	@Results(id="rmCT",value = {
		@Result(id=true,column = "id",property = "id"),//缓存性能优化用到
		@Result(column = "categoryid",property = "categoryid"),
		@Result(
			column = "categoryid",
			property = "category",
			one=@One(select = "com.zhi.zblog.dao.CategoryMapper.selectById")
		)
	})
	public List<Article> selectByNew();
	
	/**
	 * 查询热门文章
	 * 根据文章阅读量降序排序
	 * @return
	 */
	@Select("select * from article order by readCnt desc,agreeCnt desc")
	public List<Article> selectByReadcnt();
	
	/**
	 * 查询指定id的文章
	 * @param id
	 * @return
	 */
	@Select("select * from article where id=#{id}")
	//@ResultMap("rmCT")
	public Article selectById(int id);
	
	/**
	 * 新增文章
	 * @param a
	 * @return
	 */
	@Insert("insert into article "
			+ "values(null,#{author},#{title},#{content},"
			+ "#{keywords},#{description},#{categoryid},#{label},"
			+ "#{titleimgs},#{status},now(),#{readcnt},#{agreecnt})")
	@Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
	public int insert(Article a);

	/**
	 * 根据文章分类查询
	 * @param cid
	 * @return
	 */
	@Select("select * from article where categoryid=#{id} order by createtime desc")
	public List<Article> selectByCat(int cid);
}
