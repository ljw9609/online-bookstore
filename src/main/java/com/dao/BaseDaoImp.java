package com.dao;

import com.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by lvjiawei on 2017/5/7.
 */
public class BaseDaoImp implements BaseDao {
    public List query(String hql) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List q = session.createQuery(hql).list();
            session.getTransaction().commit();
            return q;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean save(Object obj) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean update(Object obj) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
