/**
 * Project Name:cms
 * File Name:ArticleVMMapper.java
 * Package Name:com.briup.apps.cms.dao.extend
 * Date:2018年9月18日下午9:13:42
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 */

package com.briup.apps.cms.dao.extend;
/**
 * ClassName:ArticleVMMapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月18日 下午9:13:42 <br/>
 *
 * @author lichunyu
 * @version
 * @see
 * @since JDK 1.6
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.briup.apps.cms.bean.extend.ArticleVM;
import org.springframework.security.core.parameters.P;

public interface ArticleVMMapper {
    ArticleVM selectById(@Param("id") long id);

    List<ArticleVM> selectAllNoPage(
            @Param("categoryId") Long categoryId,
            @Param("keywords") String keywords);

    List<ArticleVM> selectAll(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("categoryId") Long categoryId,
            @Param("keywords") String keywords,
            @Param("beginTime") String beginTime,
            @Param("endTime") String endTime);

    long count(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("categoryId") Long categoryId,
            @Param("keywords") String keywords,
            @Param("beginTime") String beginTime,
            @Param("endTime") String endTime);
}

