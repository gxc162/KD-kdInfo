/**
 * Project Name:cms
 * File Name:CategoryController.java
 * Package Name:com.briup.apps.cms.web.controller.manager
 * Date:2018年9月18日下午4:03:56
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.web.controller.manager;

import java.util.List;

import com.briup.apps.cms.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.extend.CategoryVM;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName:CategoryController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月18日 下午4:03:56 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Api(description="栏目管理")
@RestController
@RequestMapping("/manager/category")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	@ApiOperation(value="保存或更新栏目信息",notes="如果参数中id不为空表示保存，否则表示更新")
	@PostMapping(value="saveOrUpdateCategory")
	public MsgResponse saveOrUpdateCategory(Category category) {
		try {
			String result = categoryService.saveOrUpdate(category);
			return MsgResponse.success(result, category);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="批量删除栏目信息")
	@PostMapping(value="batchDeleteCategory")
	public MsgResponse batchDeleteCategory(long[] ids) {
		try {
			categoryService.batchDelete(ids);
			return MsgResponse.success("删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id删除栏目信息")
	@GetMapping(value="deleteCategoryById")
	public MsgResponse deleteCategoryById(long id) {
		try {
			categoryService.deleteById(id);
			return MsgResponse.success("删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value = "通过栏目名查询栏目信息")
	@GetMapping(value = "findCategoryByCategoryname")
	public MsgResponse findCategoryByCategoryname(String name) {
		try {
			CategoryVM user = categoryService.querryCategoryByname(name);
			return MsgResponse.success("success", user);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="通过栏目名删除栏目信息")
	@GetMapping(value="deleteCategoryByName")
	public MsgResponse deleteCategoryByName(String name) {
		try {
			categoryService.deleteByName(name);
			return MsgResponse.success("删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="查询所有栏目信息")
	@GetMapping(value="findAllCategory")
	public MsgResponse findAllCategory() {
		//List<Category> list = categoryService.queryAll();
		List<CategoryVM> list = categoryService.queryAllCategoryVM();
		return MsgResponse.success("success", list);
	}
	
	@ApiOperation(value="根据父栏目查询子栏目信息",notes="如果id为null,查询出所有以及栏目，否则查询该栏目下所有子栏目")
	@GetMapping(value="findCategoryByParentId")
	public MsgResponse findCategoryByParentId(Long id) {
		List<Category> list = categoryService.queryCategoryByParentId(id);
		return MsgResponse.success("success", list);
	}
	
}

