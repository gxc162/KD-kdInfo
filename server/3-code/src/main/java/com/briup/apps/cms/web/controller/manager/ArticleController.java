/**
 * Project Name:cms
 * File Name:ArticleController.java
 * Package Name:com.briup.apps.cms.web.controller.manager
 * Date:2018年9月18日下午3:58:50
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 */

package com.briup.apps.cms.web.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.ArticleWithBLOBs;
import com.briup.apps.cms.bean.extend.ArticleVM;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.util.MsgResponse;
import com.briup.apps.cms.vm.PageVM;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName:ArticleController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月18日 下午3:58:50 <br/>
 * @author lichunyu
 * @version
 * @since JDK 1.6
 * @see
 */
@Api(description = "文章管理")
@RestController
@RequestMapping("/manager/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "批量删除")
    @PostMapping(value = "batchDeleteArticle")
    public MsgResponse batchDeleteArticle(long[] ids) {
        try {
            articleService.batchDelete(ids);
            return MsgResponse.success("删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResponse.error(e.getMessage());
        }
    }

    @ApiOperation(value = "保存或更新", notes = "如果参数中id不为空表示保存，否则表示更新，修改只能修改基本信息，不能修改关联信息")
    @PostMapping("saveOrUpdateArticle")
    public MsgResponse saveOrUpdateArticle(ArticleWithBLOBs article, String[] fileIds) {
        try {
            ArticleVM articleVM = articleService.saveOrUpdate(article, fileIds);
            return MsgResponse.success("操作成功", articleVM);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResponse.error(e.getMessage());
        }
    }

    @ApiOperation(value = "通过id删除")
    @GetMapping("deleteArticleById")
    public MsgResponse deleteById(long id) {
        try {
            articleService.deleteById(id);
            return MsgResponse.success("删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResponse.error(e.getMessage());
        }
    }

    @ApiOperation(value = "通过id查询")
    @GetMapping("findArticleById")
    public MsgResponse findArticleById(long id) {
        try {
            ArticleVM am = articleService.queryArticleById(id);
            return MsgResponse.success("success", am);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResponse.error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询文章")
    @GetMapping("findArticle")
    public MsgResponse findArticle(int page, int pageSize, Long categoryId, String keywords, String beginTime, String endTime) {
        try {
            PageVM<ArticleVM> pageVM = articleService.queryArticle(page, pageSize, categoryId, keywords, beginTime, endTime);
            return MsgResponse.success("success", pageVM);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResponse.error(e.getMessage());
        }
    }

    @ApiOperation(value = "审核文章")
    @GetMapping("checkArticle")
    public MsgResponse checkArticle(long id, String status) {
        try {
            articleService.checkArticle(id, status);
            return MsgResponse.success("操作成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResponse.error(e.getMessage());
        }
    }
}

