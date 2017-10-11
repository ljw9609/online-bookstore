package com.dao;

import com.entity.CategoryEntity;
import com.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by lvjiawei on 2017/6/25.
 */
public class CategoryDaoImp extends BaseDaoImp implements CategoryDao {
    public List getCategory(){
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List l = session.createQuery("from CategoryEntity ").list();
            session.getTransaction().commit();
            if( l.size() > 0 ){
                return l;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getIdByName(String name){
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List l = session.createQuery("from CategoryEntity where name = ? ").setParameter(0,name).list();
            session.getTransaction().commit();
            if( l.size() > 0 ){
                return ((CategoryEntity)l.get(0)).getCategoryID();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
