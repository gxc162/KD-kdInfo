/**
 * Project Name:cms
 * File Name:ArticleVM.java
 * Package Name:com.briup.apps.cms.bean.extend
 * Date:2018年9月18日下午4:08:00
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.bean.extend;

import java.util.List;

import com.briup.apps.cms.bean.CMSFile;
import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.bean.User;

/**
 * ClassName:ArticleVM <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年9月18日 下午4:08:00 <br/>
 * 
 * @author lichunyu
 * @version
 * @since JDK 1.6
 * @see
 */
public class ArticleVM {
	private Long id;

	private String title;

	private String liststyle;

	private String publishtime;

	private String readtimes;

	private String status;

	private String content;

	private String source;
	
	private Integer thumbup;

	private Integer thumbdown;
	
	private String music;

	private String video;

	private User author;

	private Category category;

	private List<Comment> comments;

	private List<ArticleFileVM> articleFileVMs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getListstyle() {
		return liststyle;
	}

	public void setListstyle(String liststyle) {
		this.liststyle = liststyle;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	public String getReadtimes() {
		return readtimes;
	}

	public void setReadtimes(String readtimes) {
		this.readtimes = readtimes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<ArticleFileVM> getArticleFileVMs() {
		return articleFileVMs;
	}

	public void setArticleFileVMs(List<ArticleFileVM> articleFileVMs) {
		this.articleFileVMs = articleFileVMs;
	}

	public Integer getThumbup() {
		return thumbup;
	}

	public void setThumbup(Integer thumbup) {
		this.thumbup = thumbup;
	}

	public Integer getThumbdown() {
		return thumbdown;
	}

	public void setThumbdown(Integer thumbdown) {
		this.thumbdown = thumbdown;
	}
//
//	public CMSFile getMusic() {
//		return music;
//	}
//
//	public void setMusic(CMSFile music) {
//		this.music = music;
//	}
//
//	public CMSFile getVideo() {
//		return video;
//	}
//
//	public void setVideo(CMSFile video) {
//		this.video = video;
//	}

}
