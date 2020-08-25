package com.briup.apps.cms.vm;

/**
 * @Author CC
 * @Description 用户登录信息类
 * @Date 2020/2/29 13:23
 **/


public class UserVM {
    private String username;
    private String password;
    private String role;

    public UserVM(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public UserVM(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
