/**
 * Project Name:cms
 * File Name:ICommentService.java
 * Package Name:com.briup.apps.cms.service
 * Date:2018年9月19日上午10:23:56
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.bean.extend.CommentVM;
import com.briup.apps.cms.vm.PageVM;

import java.util.List;

/**
 * ClassName:ICommentService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月19日 上午10:23:56 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface ICommentService {
	List<Comment> findcommentByartileId(int id);

	void checkComment(long id,String status) throws Exception;
	
	PageVM<CommentVM> queryAll(int page,int pageSize,String keywords);
	
	void saveOrUpdate(Comment comment) throws Exception;
	
	void deleteById(long id) throws Exception;
	
	void batchDelete(long[] ids) throws Exception;

}

