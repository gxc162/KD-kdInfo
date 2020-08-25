/**
 * Project Name:cms
 * File Name:IFileService.java
 * Package Name:com.briup.apps.cms.service
 * Date:2018年9月18日下午2:29:40
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.CMSFile;

/**
 * ClassName:IFileService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月18日 下午2:29:40 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface ICMSFileService {
	void saveOrUpdate(CMSFile erpFile) throws Exception;
	
	void deleteById(String id) throws Exception;
	
	CMSFile findById(String id);
	
	List<CMSFile> findAll() ;
}

