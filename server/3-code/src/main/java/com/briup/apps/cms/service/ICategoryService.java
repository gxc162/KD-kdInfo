/**
 * Project Name:cms
 * File Name:ICategoryService.java
 * Package Name:com.briup.apps.cms.service
 * Date:2018年9月18日下午2:29:17
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.extend.CategoryVM;

/**
 * ClassName:ICategoryService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月18日 下午2:29:17 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface ICategoryService {
	long countAllCategory() ;
	
	List<CategoryVM> queryAllCategoryVM();

	CategoryVM querryCategoryByname(String name);

	List<Category> queryAll();
	
	List<Category> queryCategoryByParentId(Long id);

	String saveOrUpdate(Category category) throws Exception;
	
	void deleteById(long id) throws Exception;

	void deleteByName(String name) throws Exception;

	void batchDelete(long[] ids) throws Exception;
}

