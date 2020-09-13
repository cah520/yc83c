package com.zhi.zblog.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.zhi.zblog.biz.UserBiz;
import com.zhi.zblog.pojo.Result;
import com.zhi.zblog.pojo.User;
import com.zhi.zblog.util.BizException;
import com.zhi.zblog.util.Utils;

@Controller //默认控制器方法是执行页面跳转
public class UserAction {
	
	@Resource
	private UserBiz ubiz;

	/**
	 * 注册使用表单提交
	 * ==》页面跳转
	 * @param user
	 */
	@PostMapping("reg.do")
	public String  register(@Valid User user,Errors errors,Model m) {
		if(errors.hasErrors()) { 
			//返回所有的报错信息
			m.addAttribute("errors", Utils.asMap(errors));
			//返回用户提交的字段的信息
			m.addAttribute("user", user);  
			System.out.println( errors.getAllErrors());
			return "reg";
		}  
		try {  
			ubiz.register(user);  
		} catch (BizException e) {
			e.printStackTrace(); 
			//将我们自定义的‘用户已存在’错误注册到errors中
			errors.rejectValue("account", "account",e.getMessage());
			//返回所有的报错信息
			m.addAttribute("errors", Utils.asMap(errors));
			//返回用户提交的字段的信息
			m.addAttribute("user", user);
			return "reg";
		}
		
		//return "index";   //以请求转发的方式跳转到index
		return "redirect:/"; //使用响应重定向方法跳转  
	}
	
	/**
	 * 登录使用ajax提交
	 * ==》Vue
	 * @param user
	 */
	@GetMapping("login.do")
	@ResponseBody
	public Result login(@Valid User user,Errors errors,HttpSession session) {
		try {
			if(errors.hasFieldErrors("account") || errors.hasFieldErrors("pwd"))
			{
				Result res=new Result(0, "验证错误",errors.getFieldErrors());
				return res;
			}
			User loginedUser=ubiz.login(user);
			session.setAttribute("loginedUser", loginedUser);
			System.out.println("登录成功存入："+session.getAttribute("loginedUser"));
			
			return new Result(1, "登录成功",loginedUser);
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0, e.getMessage()); 
		}
		
	}
	
	@GetMapping("mustLogin")
	@ResponseBody
	public Result mustLogin() {
		
		return new Result(0, "请先登录");
	}
	
	@PostMapping("upload.do")
	@ResponseBody
	public Result upload(@RequestParam("head")MultipartFile file) throws IllegalStateException, IOException {
		String diskpath="D:/socket/imagsforblog/";
		String filename=file.getOriginalFilename();//文件名
		file.transferTo(new File(diskpath+filename));
		
		return new Result(1, "上传成功");  
	}  
	
	
}
