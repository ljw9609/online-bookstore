package com.actions;

import java.sql.*;
import java.util.*;

import com.service.UserService;
import org.hibernate.Session;
import org.apache.struts2.ServletActionContext;

import com.entity.UsersEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.dao.UsersDao;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.*;
import org.springframework.stereotype.Controller;

/**
 * Created by lvjiawei on 2017/4/18.
 */
@Controller
public class UserActions extends ActionSupport{
    @Qualifier("UserService")
    @Autowired
    private UserService userService;
    public void setUserService(UserService userService){this.userService = userService;System.out.println("service suc");}

    private String username;
    private String password;
    private String confirmpassword;
    private String email;

    private String oldpassword;
    private String newpassword;

    private Map params;

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public String getOldpassword(){return oldpassword;}

    public void setOldpassword(String oldpassword){this.oldpassword = oldpassword;}

    public String getNewpassword(){return newpassword;}

    public void setNewpassword(String newpassword){this.newpassword = newpassword;}

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getConfirmpassword(){
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword){
        this.confirmpassword = confirmpassword;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }


    public String login(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return userService.login(request,getUsername(),getPassword());
    }


    public String regist(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return userService.register(request,getUsername(),getPassword(),getConfirmpassword(),getEmail());
    }

    public String personinfo(){
        HttpServletRequest request = ServletActionContext.getRequest();

        List list = userService.getUserInfo(getUsername());
        request.setAttribute("list1", list);
        return SUCCESS;
    }

    public String changeinfo(){
        HttpServletRequest request = ServletActionContext.getRequest();
       return userService.changeinfo(request,getUsername(),getOldpassword(),getNewpassword(),getEmail());
    }

    public String showUserProfile() {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().setAttribute("path_nav","个人头像");
        UsersEntity user = (UsersEntity) request.getSession().getAttribute("user");
        UsersEntity user2 = userService.getUserProfile(user);

        request.getSession().setAttribute("user",user2);
        return SUCCESS;
    }

    public String updateProfile() {
        HttpServletRequest request = ServletActionContext.getRequest();
        UsersEntity u = (UsersEntity) request.getSession().getAttribute("user");
        UsersEntity user = new UsersEntity();
        user.setId(u.getId());
        userService.updateUserProfile(user);
        return SUCCESS;
    }

    public String checkUsernameAvailable(){
        params = new HashMap();
        if(userService.hasUsernameDup(getUsername())){
            params.put("result",true);
        }else{
            params.put("result",false);
        }
        return "ajax";
    }


}
