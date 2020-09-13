package com.zhi.zblog.pojo;

import java.io.Serializable;
/**
 * 文章分类的实体类
 * @author 李玲芝
 *
 */
public class Category implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private Integer sort; //排序号，默认为空
	
	private String introduce;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", sort=" + sort + ", introduce=" + introduce + "]";
	}
	
	
}
