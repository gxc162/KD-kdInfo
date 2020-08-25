package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.apps.cms.service.impl.UserService;
import com.briup.apps.cms.util.MsgResponse;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sang on 2017/12/17.
 */
@Api(description="登录注册")
/**
 * @Author CC
 * @Description 注释掉此接口
 * @Date 2020/2/21 12:49
 **/
//@RestController
public class LoginRegController {

    @Autowired
    UserService userService;

    @RequestMapping("/login_error")
    public MsgResponse loginError() {
        return MsgResponse.error("登录失败");
    }

    @RequestMapping("/login_success")
    public MsgResponse loginSuccess() {
        return MsgResponse.success("登录成功", null);
    }

    /**
     * 如果自动跳转到这个页面，说明用户未登录，返回相应的提示即可
     * <p>
     * 如果要支持表单登录，可以在这个方法中判断请求的类型，进而决定返回JSON还是HTML页面
     *
     * @return
     */
    @RequestMapping("/login_page")
    public MsgResponse loginPage() {
        return MsgResponse.error("尚未登录，请登录!");
    }

    @RequestMapping("/reg")
    public MsgResponse reg(User user) {
        String result = userService.reg(user);
//        if (result == 0) {
//            //成功
//            return MsgResponse.success("注册成功", null);
//        } else if (result == 1) {
//            return MsgResponse.error("用户名重复，注册失败!");
//        } else {
//            //失败
//            return MsgResponse.error("注册失败!");
//        }
        return MsgResponse.success("",result);

    }
}
