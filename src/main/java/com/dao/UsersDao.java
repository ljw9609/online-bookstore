package com.dao;

import com.entity.*;

import java.util.List;

/**
 * Created by lvjiawei on 2017/4/12.
 */

public interface UsersDao extends BaseDao{
    UsersEntity getUserByID(int id);
    UsersEntity getUserByUsername(String username);
    Integer getTotalUserAmount();
    boolean destroyUser(int id);
    List getUsers();
    boolean checkUserExist(String userName,String passWord);
    boolean checkIfAdmin(String userName);

    boolean hasUsernameDup(String username);
    boolean insertData(String username,String password,String reenterpassword,String email,String profile_id);
    List getUserInfo(String username);
}
