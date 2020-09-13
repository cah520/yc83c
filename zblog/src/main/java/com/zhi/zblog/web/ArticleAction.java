package com.zhi.zblog.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.zhi.zblog.biz.ArticleBiz;
import com.zhi.zblog.dao.ArticleMapper;
import com.zhi.zblog.pojo.Article;
import com.zhi.zblog.pojo.Result;
import com.zhi.zblog.pojo.User;
import com.zhi.zblog.util.Utils;

@RestController
public class ArticleAction {
	
	@Resource
	private ArticleBiz abiz;
	
	@Resource
	private ArticleMapper amapper;
	
	@GetMapping({"article"})   
	public ModelAndView article(ModelAndView mav,int id) {
		
		mav.addObject("article", amapper.selectById(id));
		mav.setViewName("article");
		return mav;     
	}  
	
	@GetMapping({"addArticle"})   
	public ModelAndView toaddArticle(ModelAndView mav) {
		
		mav.setViewName("addArticle"); 
		return mav;     
	}  

	@GetMapping("toaddArticle")  
	public Result toArticle(@SessionAttribute(name = "loginedUser",required = false)User user) {
		
		//User user=(User) request.getSession().getAttribute("loginedUser");
		
		if(user!=null) {
			return new Result(1, "已登录，前往写文章","addArticle");    
		}else {
			return new Result(0, "未登录，请先登录","index");   
		}
	}
	
	@PostMapping("addArticle.do")
	public ModelAndView addArticle(@Valid Article a,ModelAndView mav,Errors errors,@SessionAttribute("loginedUser")User user) {
		
		if(errors.hasErrors()) {
			mav.addObject("errors",Utils.asMap(errors));
			System.out.println("打印错误"+Utils.asMap(errors));
			mav.addObject("atc",a);  
			mav.setViewName("addArticle");
		}else {
			//System.out.println("写文章的作者"+user);
			//作者的名字写入文章实体类
			a.setAuthor(user.getName());
			//a.id==》有值=》数据库自增列==》mybatis=》@Options
			abiz.create(a);
			//a.getId()==》将刚刚插入的文章的id取出来
			mav.setViewName("redirect:article?id="+a.getId());
		}  
		return mav;  
	}
}
