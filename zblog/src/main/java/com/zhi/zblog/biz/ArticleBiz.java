package com.zhi.zblog.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhi.zblog.dao.ArticleMapper;
import com.zhi.zblog.pojo.Article;

@Service
public class ArticleBiz {  

	@Resource
	private ArticleMapper aMapper;
	
	public int create(Article art) { 
		return aMapper.insert(art);  
	}
}
