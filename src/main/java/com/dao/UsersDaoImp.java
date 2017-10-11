package com.dao;

import com.entity.UsersEntity;
import com.util.*;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lvjiawei on 2017/5/7.
 */
@Repository
public class UsersDaoImp extends BaseDaoImp implements UsersDao{
    public UsersEntity getUserByID(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            //UsersEntity user = (UsersEntity) session.load(UsersEntity.class, id);
            List l = session.createQuery("from UsersEntity where id=?").setParameter(0,id).list();
            session.getTransaction().commit();
            if(l.size() > 0){
                return (UsersEntity) l.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public UsersEntity getUserByUsername(String username) {
        try {
            String hql = "from UsersEntity where username=?";
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List l = session.createQuery(hql).setParameter(0,username).list();
            session.getTransaction().commit();
            if( l.size() > 0 )
                return (UsersEntity) l.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Integer getTotalUserAmount() {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "select count(*) from UsersEntity ";
            Long num = (Long) session.createQuery(hql).list().get(0);
            session.getTransaction().commit();
            return num.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean destroyUser(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            UsersEntity user = (UsersEntity) session.get(UsersEntity.class, id);
            session.delete(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List getUsers(){
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List l = session.createQuery("from UsersEntity ").list();
            session.getTransaction().commit();
            if( l.size() > 0 ){
                System.out.println("success");
                System.out.println(l.size());
                return l;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkUserExist(String userName,String passWord){
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String encodepwd = MD5Util.encode(passWord);
            List list = session.createQuery("from UsersEntity where username = '" + userName + "' and password ='" + encodepwd + "'").list();
            session.getTransaction().commit();
            if (list == null || list.size() == 0) {
                return false;
            } else {
                UsersEntity user = (UsersEntity) list.get(0);
                if (user == null) {
                    return false;
                } else {
                    System.out.println("not null");
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkIfAdmin(String userName){
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List l = session.createQuery("from UsersEntity where username = '" + userName + "'").list();
            session.getTransaction().commit();
            UsersEntity user = (UsersEntity) l.get(0);
            if (user == null) {
                return false;
            } else {
                if (user.getIs_admin() == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasUsernameDup(String username){
        UsersEntity user = getUserByUsername(username);
        if(user != null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean insertData(String username,String password,String reenterpassword,String email,String profile_id){
        String encodepwd = MD5Util.encode(password);
        String encodeconpwd = MD5Util.encode(reenterpassword);
        UsersEntity user = new UsersEntity();
        user.setUsername(username);
        user.setPassword(encodepwd);
        user.setConfirmpassword(encodeconpwd);
        user.setEmail(email);
        user.setProfile_id(profile_id);
        return save(user);
    }

    public List getUserInfo(String username){
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List list = session.createQuery("from UsersEntity where username = ?").setParameter(0, username).list();
            session.getTransaction().commit();
            if (list != null && list.size() > 0) {
                return list;
            } else {
                System.out.println("fail");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
