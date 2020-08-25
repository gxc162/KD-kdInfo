/**
 * Project Name:cms
 * File Name:IArticleService.java
 * Package Name:com.briup.apps.cms.service
 * Date:2018年9月17日下午9:35:37
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.ArticleWithBLOBs;
import com.briup.apps.cms.bean.extend.ArticleVM;
import com.briup.apps.cms.vm.PageVM;

/**
 * ClassName:IArticleService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年9月17日 下午9:35:37 <br/>
 * 
 * @author lichunyu
 * @version
 * @since JDK 1.6
 * @see
 */
public interface IArticleService {
	long countAllArticle() ;
	
	void checkArticle(long id,String status) throws Exception;
	
	ArticleVM readArticle(long id);
	
	ArticleVM queryArticleById(long id);
	
	List<ArticleVM> queryArticleNoPage(Long categoryId, String keywords);
		
	PageVM<ArticleVM> queryArticle(int page, int pageSize,Long categoryId, String keywords,String beginTime,String endTime);

	ArticleVM saveOrUpdate(ArticleWithBLOBs article,String[] fileIds) throws Exception;

	void deleteById(long id) throws Exception;

	void batchDelete(long[] ids) throws Exception;

}
