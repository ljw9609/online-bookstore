package com.service;


import com.entity.UsersEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lvjiawei on 2017/5/7.
 */

public interface UserService {
    List query(String hql);
    boolean save(Object obj);
    boolean update(Object obj);
    UsersEntity getUserById(int id);
    UsersEntity getUserByUsername(String username);
    Integer getTotalUserAmount();
    List getUsers();
    boolean deleteUserById(int id);
    boolean hasUsernameDup(String username);
    boolean checkUserExist(String userName,String passWord);
    boolean checkIfAdmin(String userName);
    boolean insertData(String username,String password,String reenterpassword,String email,String profile_id);
    List getUserInfo(String username);
    UsersEntity getUserProfile(UsersEntity user);
    boolean updateUserProfile(UsersEntity user);

    String login(HttpServletRequest request,String username,String password);
    String register(HttpServletRequest request,String username,String password,String confirmpassword,String email);
    String changeinfo(HttpServletRequest request,String username,String oldpassword,String newpassword,String email);
}
