/**
 * Project Name:cms
 * File Name:JumpController.java
 * Package Name:com.briup.apps.cms.web.controller
 * Date:2018年9月26日下午2:26:40
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

/**
 * ClassName:JumpController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月26日 下午2:26:40 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Api(description="跳转功能")
@Controller
//@RequestMapping("/jump")
public class JumpController {
	@GetMapping("toIndex")
	public String toIndex() {
		return "redirect:/jump/toList?id=1";
	}
	
	@GetMapping("toList")
	public String toList() {
		return "forward:/list.html";
	}
	
	@GetMapping("toContent")
	public String toContent() {
		return "forward:/content.html";
	}
	
	
}

