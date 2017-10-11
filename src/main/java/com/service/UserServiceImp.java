package com.service;

import com.dao.UsersDao;
import com.entity.UsersEntity;
import com.util.*;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import java.util.List;
import org.bson.types.ObjectId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lvjiawei on 2017/5/7.
 */
@Service
public class UserServiceImp implements UserService{
    @Qualifier("UsersDao")
    @Autowired
    private UsersDao userdao;

    @Autowired
    private MongoDBUtil mongo;

    public void setMongo(MongoDBUtil mongo){this.mongo = mongo;}

    public void setUserdao(UsersDao userdao){this.userdao = userdao;}

    public List query(String hql) {
        return userdao.query(hql);
    }

    public boolean save(Object obj){
        return userdao.save(obj);
    }

    public boolean update(Object obj){
        return userdao.update(obj);
    }

    public UsersEntity getUserById(int id){
        return userdao.getUserByID(id);
    }

    public UsersEntity getUserByUsername(String username){
        return userdao.getUserByUsername(username);
    }

    public Integer getTotalUserAmount(){
        return userdao.getTotalUserAmount();
    }

    public List getUsers(){
        return userdao.getUsers();
    }

    public boolean deleteUserById(int id){
        return userdao.destroyUser(id);
    }

    public boolean hasUsernameDup(String username){return userdao.hasUsernameDup(username);}

    public boolean checkUserExist(String userName,String passWord){
        return userdao.checkUserExist(userName,passWord);
    }

    public boolean checkIfAdmin(String userName){
        return userdao.checkIfAdmin(userName);
    }

    public boolean insertData(String username,String password,String reenterpassword,String email,String profile_id){ return userdao.insertData(username, password, reenterpassword, email,profile_id); }

    public List getUserInfo(String username){ return userdao.getUserInfo(username); }

    public UsersEntity getUserProfile(UsersEntity user){
        try {
            UsersEntity u = userdao.getUserByID(user.getId());
            if(u.getProfile_id().equals(""))											// the user has no profile in mongoDB
                return u;
            DBCollection collection = mongo.getDB().getCollection("profile");	// get collection of mongoDB
            BasicDBObject search = new BasicDBObject();								// search Object
            search.append("_id", new ObjectId(u.getProfile_id()));
            DBObject profile = collection.findOne(search);
            String[] field  = {"Image_id"};
            for(int i = 0; i < field.length; ++i) {
                if(profile.containsField(field[i].toLowerCase())) {
                    Method m = u.getClass().getMethod("set" + field[i], String.class);
                    m.invoke(u, profile.get(field[i].toLowerCase()));
                }
            }
            return u;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateUserProfile(UsersEntity user) {
        try {

            UsersEntity u = userdao.getUserByID(user.getId());
            //u.setName(user.getName());												// update name
            DBCollection collection = mongo.getDB().getCollection("profile");	// get collection of mongoDB
            BasicDBObject search = new BasicDBObject();								// search Object
            if(u.getProfile_id().equals("")) {										// if the user do not has profileID, insert it into mongo and get profileID
                collection.insert(search);
                u.setProfile_id(search.get("_id").toString());
            } else {																// or get the search object
                search.put("_id", new ObjectId(u.getProfile_id()));
            }

            BasicDBObject updateObject = new BasicDBObject();
            String[] field  = {"Image_id"};
            for(int i = 0; i < field.length; ++i) {
                Method m = UsersEntity.class.getMethod("get" + field[i]);
                Object tmp = m.invoke(user);
                if(tmp != null && !tmp.equals(""))
                    updateObject.append(field[i].toLowerCase(), tmp.toString());
            }

            //BasicDBObject updateQuery = new BasicDBObject();						// create the update object
            //updateQuery.append("$set", updateObject);
            collection.update(search, updateObject);									// update profile in mongoDB
            userdao.update(u);													// update user in mysql
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String login(HttpServletRequest request,String username,String password){
        if (username.equals("")) {
            String msg = "Username cannot be null";
            request.setAttribute("tipMessage", msg);
            return "loginfail";
        }
        if (checkUserExist(username, password)) {
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("password", password);

            if (checkIfAdmin(username)) {
                request.getSession().setAttribute("is_admin", "admin");
            } else {
                UsersEntity user = getUserByUsername(username);
                System.out.println("login:"+user);
                request.getSession().setAttribute("user",user);
                request.getSession().setAttribute("is_admin", "user");
            }
            request.setAttribute("tipMessage", "Login succedd!Welcome " + username + "");
            return "loginsuccess";
        } else {
            System.out.println("fail");
            request.setAttribute("tipMessage", "No such username or password error!");
            return "loginfail";
        }
    }

    public String register(HttpServletRequest request,String username,String password,String confirmpassword,String email){
        if(username.equals("")){
            request.setAttribute("tipMessage","username cannot be null");
            return "registfail";
        }
        if(password.equals("")){
            request.setAttribute("tipMessage","password cannot be null");
            return "registfail";
        }
        if(!password.equals(confirmpassword)){
            request.setAttribute("tipMessage","two password not match");
            System.out.println("pwd fail");
            return "registfail";
        }

        if(hasUsernameDup(username)){
            request.setAttribute("tipMessage","username existed!");
            return "registfail";
        }
        else{
            if(insertData(username,password,confirmpassword,email,"")){
                request.setAttribute("tipMessage","regist succeed");
                return "registsuccess";
            }
            else{
                request.setAttribute("tipMessage","regist fail");
                return "registfail";
            }
        }
    }

    public String changeinfo(HttpServletRequest request,String username,String oldpassword,String newpassword,String email){
        UsersEntity user = getUserByUsername(username);
        int id = user.getId();

        if(checkUserExist(username,oldpassword)){
            user.setId(id);
            user.setUsername(username);
            user.setPassword(MD5Util.encode(newpassword));
            user.setConfirmpassword(MD5Util.encode(newpassword));
            user.setEmail(email);
            boolean flag = update(user);
            if(flag){
                request.setAttribute("tipMessage","Update success!");
                return "success";
            }
        }else{
            request.setAttribute("tipMessage","password error!");
            return "fail";
        }
        return "fail";
    }
}
