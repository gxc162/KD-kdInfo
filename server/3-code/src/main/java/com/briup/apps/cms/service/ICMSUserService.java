/**
 * Project Name:cms
 * File Name:ICMSUserService.java
 * Package Name:com.briup.apps.cms.service
 * Date:2018年9月18日下午4:45:51
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 */

package com.briup.apps.cms.service;
/**
 * ClassName:ICMSUserService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月18日 下午4:45:51 <br/>
 * @author lichunyu
 * @version
 * @since JDK 1.6
 * @see
 */

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.UserExtend;

import java.util.List;

public interface ICMSUserService {
    long countAllUsers();

    User findUser(String username, String password, String type);

    List<User> query();

    void deleteById(int id) throws Exception;

    User findUserByUsername(String username) throws Exception;

    User findUserById(int id) throws Exception;

    void saveOrUpdate(User user) throws Exception;

    void changeStatus(int id, boolean status) throws Exception;
}

