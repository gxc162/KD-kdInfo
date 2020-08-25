/**
 * Project Name:cms
 * File Name:ArticleController.java
 * Package Name:com.briup.apps.cms.web.controller
 * Date:2018年9月17日下午9:19:20
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.extend.ArticleVM;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName:ArticleController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月17日 下午9:19:20 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Api(description="首页功能")
@RestController
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IArticleService articleService;
	
	@ApiOperation(value="查询所有栏目")
	@GetMapping(value="findAllCategory")
	public MsgResponse findAllCategory() {
		List<Category> list = categoryService.queryAll();
		return MsgResponse.success("success", list);
	}
	
	@ApiOperation(value="所有栏目下所有文章")
	@GetMapping(value="findArticleByCategoryId")
	public MsgResponse findArticleByCategoryId(Long categoryId,String keywords) {
		List<ArticleVM> list = articleService.queryArticleNoPage(categoryId, keywords);
		return MsgResponse.success("success", list);
	}
	
	@ApiOperation(value="阅读文章")
	@GetMapping(value="readArticle")
	public MsgResponse readArticle(long id) {
		ArticleVM article = articleService.readArticle(id);
		return MsgResponse.success("success", article);
	}



}


