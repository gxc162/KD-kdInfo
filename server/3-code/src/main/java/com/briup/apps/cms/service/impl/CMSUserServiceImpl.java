/**
 * Project Name:cms
 * File Name:CMSUserServiceImpl.java
 * Package Name:com.briup.apps.cms.service.impl
 * Date:2018年9月18日下午4:46:08
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 */

package com.briup.apps.cms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.UserExample;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.dao.UserMapper;
import com.briup.apps.cms.dao.extend.UserExtendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.cms.dao.CMSUserMapper;
import com.briup.apps.cms.service.ICMSUserService;
import org.springframework.util.DigestUtils;

/**
 * ClassName:CMSUserServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月18日 下午4:46:08 <br/>
 *
 * @author lichunyu
 * @see
 * @since JDK 1.6
 */
@Service
public class CMSUserServiceImpl implements ICMSUserService {
    @Autowired
    private CMSUserMapper cmsUserMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserExtendMapper userExtendMapper;

    @Override
    public List<User> query() {
        UserExample example = new UserExample();
        List<User> list = userMapper.selectByExample(example);
        return list;
    }

    @Override
    public User findUser(String username, String password, String type) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
//		criteria.andTypeEqualTo(type);
        List<User> list = userMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void deleteById(int id) throws Exception {
        cmsUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User findUserByUsername(String username) throws Exception {
        User user = userExtendMapper.loadUserByUsername(username);
        return user;
    }

    @Override
    public User findUserById(int id) throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
    @Override
    public void saveOrUpdate(User user) throws Exception {
        if (user.getId() == null) {
            // user.setRegtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            // 先加密密码
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            // 添加注册时间
            user.setRegtime(new Date());
            userMapper.insert(user);
        } else {
            userMapper.updateByPrimaryKey(user);
        }
    }

    @Override
    public void changeStatus(int id, boolean status) throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
        user.setEnabled(status);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public long countAllUsers() {
        UserExample example = new UserExample();
        return cmsUserMapper.countByExample(example);
    }

}

