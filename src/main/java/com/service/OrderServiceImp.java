package com.service;

import com.dao.OrdersDao;
import com.entity.OrdersEntity;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvjiawei on 2017/5/8.
 */
@Service
public class OrderServiceImp implements OrderService {
    @Qualifier("OrdersDao")
    @Autowired
    private OrdersDao orderdao;

    public void setOrderdao(OrdersDao orderdao){this.orderdao = orderdao;}

    public  List query(String hql){return orderdao.query(hql);}

    public boolean save(Object obj){return orderdao.save(obj);}

    public boolean update(Object obj){return orderdao.update(obj);}

    public int getLastId(){return orderdao.getLastId();}

    public List getOrderByUsername(String username){return orderdao.getOrderByUsername(username);}

    public OrdersEntity getOrderByID(int id){return orderdao.getOrderByID(id);}

    public Integer getTotalOrderAmount(){return orderdao.getTotalOrderAmount();}

    public Integer getUserOrderAmount(String username){return orderdao.getUserOrderAmount(username);}

    public boolean destroyOrder(int id){return orderdao.destroyOrder(id);}

    public List getAllOrder(){return orderdao.getAllOrder();}

    public List getOrderById_list(int id){return orderdao.getOrderById_list(id);}

    public List statistic(String method){return orderdao.statistic(method);}

    public Double saleby(String method){
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List list = new ArrayList();
            if(method.length() == 4){
                int year = Integer.valueOf(method);
                Query query = session.createQuery("select sum(total_price) from OrdersEntity where year(orderdate) = "+year+"");
                list = query.list();
            }
            else if(method.indexOf("[")!=-1){
                int bookid = (int)session.createQuery("select id from BooksEntity where title = ?").setParameter(0,method).list().get(0);
                System.out.println("bookid:"+bookid);
                list = session.createQuery("select sum(price) from OrderItemEntity where book_id = "+bookid+"").list();
            }
            else{
                Query query = session.createQuery("select sum(total_price) from OrdersEntity where username = ?");
                query.setParameter(0,method);
                list = query.list();
            }
            session.getTransaction().commit();
            if(list.size() > 0){
                Double result = (Double)list.get(0);
                return result;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
