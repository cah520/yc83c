package com.zhi.zblog.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Comment implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@Min(1)  //int类型不能使用@NotEmpty来进行判断
	private Integer articleid;
	@NotEmpty(message = "评论内容不能为空")
	private String content;
	private Integer createby;
	private Timestamp createtime;
	
	private Article article; // 关联文章   一对一关联
	private User user; 		 // 发表人, 关联 User  一对一关联
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticleid() {
		return articleid;
	}
	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCreateby() {
		return createby;
	}
	public void setCreateby(Integer createby) {
		this.createby = createby;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
}
