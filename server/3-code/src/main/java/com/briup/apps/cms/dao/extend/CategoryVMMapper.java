/**
 * Project Name:cms
 * File Name:CategoryVMMapper.java
 * Package Name:com.briup.apps.cms.dao.extend
 * Date:2018年9月25日上午9:18:17
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.dao.extend;
/**
 * ClassName:CategoryVMMapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月25日 上午9:18:17 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

import java.util.List;

import com.briup.apps.cms.bean.extend.CategoryVM;

public interface CategoryVMMapper {
	List<CategoryVM> selectAll() ;
	CategoryVM loadUserByUsername(String name);
}

