package com.zhi.zblog.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zhi.zblog.biz.CommentBiz;
import com.zhi.zblog.pojo.Comment;
import com.zhi.zblog.pojo.Result;
import com.zhi.zblog.pojo.User;
import com.zhi.zblog.util.Utils;

@RestController
public class CommentAction {
	
	@Resource
	private CommentBiz cbiz;

	@PostMapping("comment.do")
	public Result comment(@Valid Comment comm,Errors errors,@SessionAttribute("loginedUser")User user) {
		if(errors.hasErrors())
		{
			Result res=new Result(0, "评论内容不能为空",errors.getFieldErrors());
			return res;
		}
		
		comm.setCreateby(user.getId());
		cbiz.create(comm);
		
		return new Result(1, "回复成功",comm);
	}
	
	@GetMapping("queryComm")
	public Result comment(int articleid) {
		return new Result(1, "查询回复成功",cbiz.selectByArticle(articleid));
	}
	
}
