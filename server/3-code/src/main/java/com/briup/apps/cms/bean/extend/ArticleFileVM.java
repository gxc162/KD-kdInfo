/**
 * Project Name:cms
 * File Name:ArticleFileVM.java
 * Package Name:com.briup.apps.cms.bean.extend
 * Date:2018年9月21日下午2:31:58
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.bean.extend;
/**
 * ClassName:ArticleFileVM <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月21日 下午2:31:58 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

import com.briup.apps.cms.bean.CMSFile;

public class ArticleFileVM {
	private Long id;
	private Long articleId;
	private CMSFile cmsFile;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public CMSFile getCmsFile() {
		return cmsFile;
	}
	public void setCmsFile(CMSFile cmsFile) {
		this.cmsFile = cmsFile;
	}
}

