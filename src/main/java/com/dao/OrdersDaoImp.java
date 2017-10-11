package com.dao;

import com.entity.OrdersEntity;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvjiawei on 2017/5/8.
 */
@Repository
public class OrdersDaoImp extends BaseDaoImp implements OrdersDao {
    public int getLastId(){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "select max(id) from OrdersEntity ";
            int last_id = (int) session.createQuery(hql).list().get(0);
            session.getTransaction().commit();
            return last_id;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public List getOrderByUsername(String username){
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List list = session.createQuery("from OrdersEntity where username = ?").setParameter(0,username).list();
            session.getTransaction().commit();
            if(list.size()>0 ){
                return list;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public OrdersEntity getOrderByID(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            OrdersEntity order = (OrdersEntity) session.load(OrdersEntity.class, id);
            session.getTransaction().commit();
            return order;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getTotalOrderAmount() {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "select count(*) from OrdersEntity ";
            Long num = (Long) session.createQuery(hql).list().get(0);
            session.getTransaction().commit();
            return num.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getUserOrderAmount(String username) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "select count(*) from OrdersEntity where username = ?";
            Long num = (Long) session.createQuery(hql).list().get(0);
            session.getTransaction().commit();
            return num.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean destroyOrder(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            OrdersEntity order = (OrdersEntity) session.get(OrdersEntity.class, id);
            session.delete(order);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List getAllOrder(){
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List list = session.createQuery("from OrdersEntity ").list();
            session.getTransaction().commit();
            if(list.size() > 0){
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List getOrderById_list(int id){
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List list = session.createQuery("from OrdersEntity where id = ?").setParameter(0,id).list();
            session.getTransaction().commit();
            if(list.size() > 0){
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List statistic(String method){
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List list = new ArrayList();
            if(method.length() == 4){
                int year = Integer.valueOf(method);
                Query query = session.createQuery("from OrdersEntity where year(orderdate) = "+year+"");
                list = query.list();
            }
            else{
                Query query = session.createQuery("from OrdersEntity where username = ?");
                query.setParameter(0,method);
                list = query.list();
            }

            session.getTransaction().commit();
            if(list.size() > 0)
                return list;
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
