/**
 * Project Name:cms
 * File Name:ArticleFileVMMapper.java
 * Package Name:com.briup.apps.cms.dao.extend
 * Date:2018年9月21日下午2:33:59
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.dao.extend;
/**
 * ClassName:ArticleFileVMMapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月21日 下午2:33:59 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.briup.apps.cms.bean.extend.ArticleFileVM;

public interface ArticleFileVMMapper {
	List<ArticleFileVM> selectByArticleId(@Param("id") long id);
}

