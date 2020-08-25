/**
 * Project Name:cms
 * File Name:CommentVMMapper.java
 * Package Name:com.briup.apps.cms.dao.extend
 * Date:2018年9月19日上午10:58:38
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.dao.extend;
/**
 * ClassName:CommentVMMapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月19日 上午10:58:38 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.bean.extend.CommentVM;
@Mapper
public interface CommentVMMapper {
	List<CommentVM> selectAll(
			@Param("page") int page,
			@Param("pageSize") int pageSize,
			@Param("keywords") String keywords);
	
	long count(@Param("keywords") String keywords);
	
	List<Comment> selectCommentByArticleId(@Param("id") long id);
}

