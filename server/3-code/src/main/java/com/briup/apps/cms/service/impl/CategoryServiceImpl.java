/**
 * Project Name:cms
 * File Name:CategoryServiceImpl.java
 * Package Name:com.briup.apps.cms.service.impl
 * Date:2018年9月18日下午2:30:29
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.service.impl;

import java.util.Date;
import java.util.List;

import com.briup.apps.cms.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.bean.CategoryExample.Criteria;
import com.briup.apps.cms.bean.extend.CategoryVM;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.dao.extend.CategoryVMMapper;
import com.briup.apps.cms.service.ICategoryService;

/**
 * ClassName:CategoryServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月18日 下午2:30:29 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private CategoryVMMapper categoryVMMapper;
	
	@Override
	public List<CategoryVM> queryAllCategoryVM() {
		List<CategoryVM> list = categoryVMMapper.selectAll();
		return list;
	}

	@Override
	public CategoryVM querryCategoryByname(String name) {
		CategoryVM category = categoryVMMapper.loadUserByUsername(name);
		return category;
	}

	@Override
	public List<Category> queryAll() {
		CategoryExample example = new CategoryExample();
		List<Category> list = categoryMapper.selectByExample(example);
		return list;
	}

	@Override
	public String saveOrUpdate(Category category) throws Exception {
		CategoryVM loadUserByUsername = categoryVMMapper.loadUserByUsername(category.getName());
			if (category.getId() == null) {
				// 注册用户
				if (loadUserByUsername != null) {
					return "该栏目名已存在，请换一个";
				} else {
				    categoryMapper.insert(category);
					return "添加成功";
				}
			} else {
				// 修改用户
				if (category.getName().equals(categoryMapper.selectByPrimaryKey(category.getId()).getName()) || loadUserByUsername == null) {
					categoryMapper.updateByPrimaryKey(category);
					return "更改信息成功";
				} else {
					return "该栏目名已存在，请换一个";
				}
			}
	}

	@Override
	public void deleteById(long id) throws Exception {
		categoryMapper.deleteByPrimaryKey(id);
	}

    @Override
    public void deleteByName(String name) throws Exception {
        CategoryVM category = categoryVMMapper.loadUserByUsername(name);
        categoryMapper.deleteByPrimaryKey(category.getId());
    }

    @Override
	public void batchDelete(long[] ids) throws Exception {
		if(ids != null && ids.length>0) {
			for(long id : ids) {
				categoryMapper.deleteByPrimaryKey(id);
			}
		} else {
			throw new Exception("参数有误");
		}
	}

	@Override
	public List<Category> queryCategoryByParentId(Long id) {
		CategoryExample example = new CategoryExample();
		Criteria criteria = example.createCriteria();
		if(id ==null) {
			criteria.andParentIdIsNull();
		} else {
			criteria.andParentIdEqualTo(id);
		}
		List<Category> list = categoryMapper.selectByExample(example);
		return list;
	}

	@Override
	public long countAllCategory() {
		CategoryExample example = new CategoryExample();
		return categoryMapper.countByExample(example);
	}

}

