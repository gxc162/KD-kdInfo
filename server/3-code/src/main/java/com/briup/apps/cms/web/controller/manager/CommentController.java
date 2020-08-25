/**
 * Project Name:cms
 * File Name:CommentController.java
 * Package Name:com.briup.apps.cms.web.controller.manager
 * Date:2018年9月19日上午11:10:04
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.web.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.bean.extend.CommentVM;
import com.briup.apps.cms.service.ICommentService;
import com.briup.apps.cms.util.MsgResponse;
import com.briup.apps.cms.vm.PageVM;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * ClassName:CommentController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月19日 上午11:10:04 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Api(description="评论管理")
@RestController
@RequestMapping("/manager/comment")
public class CommentController {
	
	@Autowired
	private ICommentService commentService;
	
	@ApiOperation(value="查询评论")
	@GetMapping(value="findComment")
	public MsgResponse findComment(int page, int pageSize,String keywords) {
		PageVM<CommentVM> pageVM = commentService.queryAll(page, pageSize, keywords);
		return MsgResponse.success("success", pageVM);
	}

	@ApiOperation(value="根据文章id查询评论")
	@GetMapping(value="findCommentByArticleId")
	public MsgResponse findCommentByArticleId(int id) {
		return MsgResponse.success("success", commentService.findcommentByartileId(id));
	}

	@ApiOperation(value="保存或更新评论",notes="如果参数中id不为空表示保存，否则表示更新")
	@PostMapping(value="saveOrUpdateComment")
	public MsgResponse saveOrUpdateComment(Comment comment) {
		try {
			commentService.saveOrUpdate(comment);
			return MsgResponse.success("操作成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="批量删除评论")
	@PostMapping(value="batchDeleteComment")
	public MsgResponse batchDeleteComment(long[] ids) {
		try {
			commentService.batchDelete(ids);
			return MsgResponse.success("删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="根据ID删除评论")
	@GetMapping(value="deleteCommentById")
	public MsgResponse deleteCommentById(long id) {
		try {
			commentService.deleteById(id);
			return MsgResponse.success("删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="审核评论",notes="status的取值只能为\"审核通过\"，\"审核未通过\"")
	@GetMapping(value="checkComment")
	public MsgResponse checkComment(long id,String status) {
		try {
			commentService.checkComment(id, status);
			return MsgResponse.success("操作成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
}

