package com.zhi.zblog.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.zhi.zblog.dao.ArticleMapper;
import com.zhi.zblog.dao.CategoryMapper;

@Controller
public class IndexAction {
	
	@Resource
	private ArticleMapper amapper;
	
	@Resource
	private CategoryMapper cmapper;

	@GetMapping({"/","/index.html","index"})   
	public String index(Model model,@RequestParam(defaultValue = "1")int page) {
		
		//在执行查询前，设置分页参数  
		//注意：必须是在查询方法执行前，调用分页参数设置   
		PageHelper.startPage(page,5);   
		//加入文章信息
		model.addAttribute("alist", amapper.selectByNew());
		//加入分类信息
		model.addAttribute("clist", cmapper.selectAll());
		//加入热门文章
		model.addAttribute("hlist",amapper.selectByReadcnt());
		
		return "index";             
	}
	
	
	
	@GetMapping({"category"})   
	public String category(Model model,int id) {
		
		model.addAttribute("articlebycat", amapper.selectByCat(id));
		return "category";     
	}
	
	@GetMapping("reg")  
	public String toReg() {
		return "reg";     
	}
	
	
	
}
