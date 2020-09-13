package com.zhi.zblog.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhi.zblog.dao.CommentMapper;
import com.zhi.zblog.pojo.Comment;

@Service
public class CommentBiz {
	
	@Resource
	private CommentMapper cmapper;

	public void create(Comment comm) {
		cmapper.insert(comm);
	}
	

	public List<Comment> selectByArticle(int articleid) {
	
		return cmapper.selectByArticle(articleid);
	}
}
