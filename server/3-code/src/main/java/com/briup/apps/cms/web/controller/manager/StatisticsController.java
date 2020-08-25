/**
 * Project Name:cms
 * File Name:StatisticsController.java
 * Package Name:com.briup.apps.cms.web.controller.manager
 * Date:2018年9月26日下午1:37:23
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.web.controller.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.service.ICMSUserService;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName:StatisticsController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月26日 下午1:37:23 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Api(description="统计管理")
@RestController
@RequestMapping("/manager/statistics")
public class StatisticsController {
	@Autowired
	private IArticleService articleService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private ICMSUserService cmsUserService;
	
	@ApiOperation(value="获取文章总篇数，栏目总个数，用户总个数")
	@GetMapping("getCount")
	public MsgResponse getCount() {
		long articleCount = articleService.countAllArticle();
		long categoryCount = categoryService.countAllCategory();
		long userCount = cmsUserService.countAllUsers();
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("article", articleCount);
		map.put("category", categoryCount);
		map.put("user", userCount);
		return MsgResponse.success("success", map);
	}
}

