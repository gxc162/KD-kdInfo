/**
 * Project Name:cms
 * File Name:UserServiceImpl.java
 * Package Name:com.briup.apps.cms.service.impl
 * Date:2018年9月17日下午9:35:52
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 */

package com.briup.apps.cms.service.impl;

import java.util.Date;
import java.util.List;

import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.dao.RolesMapper;
import com.briup.apps.cms.dao.extend.UserExtendMapper;
import org.springframework.util.DigestUtils;

/**
 * ClassName:UserServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年9月17日 下午9:35:52 <br/>
 *
 * @author lichunyu
 * @see
 * @since JDK 1.6
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserExtendMapper userExtendMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RolesMapper rolesMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserExtend user = userExtendMapper.loadUserByUsernameExtend(s);
        if (user == null) {
            // 避免返回null，这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
            return new UserExtend();
        }
        // 查询用户的角色信息，并返回存入user中
//		List<Role> roles = rolesMapper.getRolesByUid(user.getId());
//		user.setRoles(roles);
        return user;
    }

    /**
     * @return java.lang.String
     * @Author CC
     * @Description 注册或更改用户信息接口
     * @Date 2020/2/29 16:35
     * @Param [user]
     **/

    public String reg(User user) {
        User loadUserByUsername = userExtendMapper.loadUserByUsername(user.getUsername());
        if (user.getId() == null) {
            // 注册用户
            if (loadUserByUsername != null) {
                return "该用户名已被占用，请换一个";
            } else {
                // 插入用户,插入之前先对密码进行加密
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                // 添加注册时间
                user.setRegtime(new Date());
                userExtendMapper.reg(user);
                return "注册成功";
            }
        } else {
            // 修改用户
            if (user.getUsername().equals(userMapper.selectByPrimaryKey(user.getId()).getUsername()) || loadUserByUsername == null) {
                if (!user.getPassword().equals(userMapper.selectByPrimaryKey(user.getId()).getPassword())) {
                    //  如果密码改变则再次加密
                    user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                }
                user.setRegtime(userMapper.selectByPrimaryKey(user.getId()).getRegtime());
                userMapper.updateByPrimaryKey(user);
                return "更改信息成功";
            } else {
                return "该用户名已被占用，请换一个";
            }
        }
    }

//	public int updateUserEmail(String email) {
//		return userMapper.updateUserEmail(email, Util.getCurrentUser().getId());
//	}

    public List<User> getUserByNickname(String nickname) {
        List<User> list = userExtendMapper.getUserByNickname(nickname);
        return list;
    }

//	public List<Role> getAllRole() {
//		return userExtendMapper.getAllRole();
//	}

    public int updateUserEnabled(Boolean enabled, Long uid) {
        return userExtendMapper.updateUserEnabled(enabled, uid);
    }

    public int deleteUserById(Long uid) {
        return userExtendMapper.deleteUserById(uid);
    }

    public int updateUserRoles(Long[] rids, Long id) {
        return userExtendMapper.setUserRoles(rids, id);
    }

    public User getUserById(Long id) {
        return userExtendMapper.getUserById(id);
    }
}
