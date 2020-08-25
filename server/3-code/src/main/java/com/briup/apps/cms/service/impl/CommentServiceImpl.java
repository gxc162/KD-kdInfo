/**
 * Project Name:cms
 * File Name:CommentServiceImpl.java
 * Package Name:com.briup.apps.cms.service.impl
 * Date:2018年9月19日上午10:24:09
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.bean.extend.CommentVM;
import com.briup.apps.cms.dao.CommentMapper;
import com.briup.apps.cms.dao.extend.CommentVMMapper;
import com.briup.apps.cms.service.ICommentService;
import com.briup.apps.cms.vm.PageVM;

/**
 * ClassName:CommentServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月19日 上午10:24:09 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
public class CommentServiceImpl implements ICommentService {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private CommentVMMapper commentVMMapper;
	
	@Override
	public PageVM<CommentVM> queryAll(int page,int pageSize,String keywords) {
		if(keywords!=null) {
			keywords = "%"+keywords+"%";
		}
		List<CommentVM> list = commentVMMapper.selectAll(page, pageSize, keywords);
		long total = commentVMMapper.count(keywords);

		PageVM<CommentVM> pageVM = new PageVM<>(page, pageSize, total, list);
		return pageVM;
	}

	@Override
	public void saveOrUpdate(Comment comment) throws Exception {
		if(comment.getId() != null) {
			commentMapper.updateByPrimaryKey(comment);
		} else {
			//插入
			comment.setCommenttime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			comment.setStatus("未审核");
			commentMapper.insert(comment);
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		commentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		if(ids!=null && ids.length>0) {
			for(long id : ids) {
				commentMapper.deleteByPrimaryKey(id);
			}
		} else {
			throw new Exception("参数异常");
		}
	}

	@Override
	public List<Comment> findcommentByartileId(int id) {
		return commentVMMapper.selectCommentByArticleId(id);
	}

	@Override
	public void checkComment(long id, String status) throws Exception {
		Comment comment = commentMapper.selectByPrimaryKey(id);
		if(comment!=null) {
			if(comment.getStatus().equals("未审核")) {
				comment.setStatus(status);
				commentMapper.updateByPrimaryKey(comment);
			} else {
				throw new Exception("不能重复审核");
			}
			
		} else {
			throw new Exception("评论不存在");
		}
	}
}

