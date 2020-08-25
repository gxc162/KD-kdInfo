/**
 * Project Name:cms
 * File Name:ArticleServiceImpl.java
 * Package Name:com.briup.apps.cms.service.impl
 * Date:2018年9月18日下午2:30:04
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleCMSFile;
import com.briup.apps.cms.bean.ArticleCMSFileExample;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.bean.ArticleWithBLOBs;
import com.briup.apps.cms.bean.extend.ArticleFileVM;
import com.briup.apps.cms.bean.extend.ArticleVM;
import com.briup.apps.cms.dao.ArticleCMSFileMapper;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.CMSFileMapper;
import com.briup.apps.cms.dao.extend.ArticleVMMapper;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.util.FastDFS;
import com.briup.apps.cms.vm.PageVM;

/**
 * ClassName:ArticleServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月18日 下午2:30:04 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
public class ArticleServiceImpl implements IArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ArticleVMMapper articleVMMapper;
	@Autowired
	private ArticleCMSFileMapper articleCMSFileMapper;
	@Autowired
	private CMSFileMapper cmsFileMapper;
	
	@Override
	public ArticleVM queryArticleById(long id) {
		return articleVMMapper.selectById(id);
	}

	
	@Override
	public List<ArticleVM> queryArticleNoPage(Long categoryId, String keywords) {
		if(keywords!=null) {
			keywords = "%"+keywords+"%";
		}
		List<ArticleVM> list = articleVMMapper.selectAllNoPage(categoryId, keywords);
		return list;
	}
	
	@Override
	public PageVM<ArticleVM> queryArticle(int page, int pageSize, Long categoryId,String keywords,String beginTime,String endTime) {
		if(keywords!=null) {
			keywords = "%"+keywords+"%";
		}
		List<ArticleVM> list = articleVMMapper.selectAll(page, pageSize, categoryId, keywords,beginTime,endTime);
		long total = articleVMMapper.count(page, pageSize, categoryId, keywords,beginTime,endTime);
		PageVM<ArticleVM> pageVM = new PageVM<>(page, pageSize, total, list);
		return pageVM;
	}

	@Override
	public ArticleVM saveOrUpdate(ArticleWithBLOBs article,String[] fileIds) throws Exception {
		if(article.getId() == null) {
			// 初始化
			article.setPublishtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			article.setReadtimes(0);
			article.setThumbdown(0);
			article.setThumbup(0);
			article.setStatus("未审核");
			if(article.getListstyle() == null) {
				article.setListstyle("style-one");
			}
			//执行插入操作
			articleMapper.insert(article);
			//保存附件
			if(fileIds != null) {
				for(String fileId : fileIds) {
					System.out.println("------------fileId:"+fileId+",articleId:"+article.getId());
					ArticleCMSFile acf = new ArticleCMSFile();
					acf.setFileId(fileId);
					acf.setArticleId(article.getId());
					articleCMSFileMapper.insert(acf);
				}
			}
		} else {
			// 更新操作
			articleMapper.updateByPrimaryKey(article);
			//保存附件
			if(fileIds != null) {
				//[1,2,3]
				for(String fileId : fileIds) {
					// 1. 通过fileId查询桥表是否与某一文章关联，如果关联不做操作，否则插入
					
					//通过fileId查找附件文章关系
					ArticleCMSFileExample acfe = new ArticleCMSFileExample();
					acfe.createCriteria().andFileIdEqualTo(fileId);
					List<ArticleCMSFile> list = articleCMSFileMapper.selectByExample(acfe);
					
					System.out.println(fileId+"====>"+list.size());
					//如果为空表示附件被删除了
					if(list.isEmpty()) {
						// 插入新的记录
						ArticleCMSFile acf = new ArticleCMSFile();
						acf.setFileId(fileId);
						acf.setArticleId(article.getId());
						articleCMSFileMapper.insert(acf);
					} 
					
				}
			}
			
		}
		//返回
		return this.queryArticleById(article.getId());
		
	}

	@Override
	public void deleteById(long id) throws Exception {
		ArticleVM avm = articleVMMapper.selectById(id);
		if(avm != null) {
			//删除数据之前先删除相关附件
			List<ArticleFileVM> afvms = avm.getArticleFileVMs();
			if(afvms != null) {
				for(ArticleFileVM afvm : afvms) {
					String fileId = afvm.getCmsFile().getId();
					// 删除附件
					FastDFS.delete(fileId);
					// 删除附件与文字关联记录
					articleCMSFileMapper.deleteByPrimaryKey(afvm.getId());
					// 删除附件记录
					cmsFileMapper.deleteByPrimaryKey(fileId);
				}
			}
			//删除文章数据
			articleMapper.deleteByPrimaryKey(id);
		} else {
			throw new Exception("要删除的文件不存在");
		}
		
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		if(ids!=null && ids.length>0) {
			for(long id : ids) {
				this.deleteById(id);
			}
		} else {
			throw new Exception("参数异常");
		}
	}


	@Override
	public ArticleVM readArticle(long id) {
		Article article = articleMapper.selectByPrimaryKey(id);
		if(article!=null) {
			//更新阅读次数
			article.setReadtimes(article.getReadtimes()+1);
			articleMapper.updateByPrimaryKey(article);
		}
		//查询文章
		ArticleVM articleVM = articleVMMapper.selectById(id);
		
		return articleVM;
	}


	@Override
	public void checkArticle(long id, String status) throws Exception {
		Article article = articleMapper.selectByPrimaryKey(id);
		if(article!=null) {
			if(article.getStatus().equals("未审核")) {
				article.setStatus(status);
				articleMapper.updateByPrimaryKey(article);
			} else {
				throw new Exception("不能重复审核");
			}
			
		} else {
			throw new Exception("文章不存在");
		}
	}


	@Override
	public long countAllArticle() {
		ArticleExample example = new ArticleExample();
		return articleMapper.countByExample(example);
	}

}

