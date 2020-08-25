/**
 * Project Name:cms
 * File Name:CMSFileServiceImpl.java
 * Package Name:com.briup.apps.cms.service.impl
 * Date:2018年9月18日下午2:30:58
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * ClassName:CMSFileServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月18日 下午2:30:58 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.CMSFile;
import com.briup.apps.cms.bean.CMSFileExample;
import com.briup.apps.cms.dao.CMSFileMapper;
import com.briup.apps.cms.service.ICMSFileService;

@Service
public class CMSFileServiceImpl implements ICMSFileService {
	@Autowired
	private CMSFileMapper cmsFileMapper;

	@Override
	public void saveOrUpdate(CMSFile erpFile) throws Exception {
		cmsFileMapper.insert(erpFile);
	}

	@Override
	public void deleteById(String id) throws Exception {
		cmsFileMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CMSFile findById(String id) {
		CMSFile erpFile = cmsFileMapper.selectByPrimaryKey(id);
		return erpFile;
	}

	@Override
	public List<CMSFile> findAll() {
		CMSFileExample example = new CMSFileExample();
		List<CMSFile> list = cmsFileMapper.selectByExample(example);
		return list;
	}
}

