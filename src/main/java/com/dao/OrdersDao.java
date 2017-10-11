package com.dao;

import com.entity.*;

import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import com.util.HibernateUtil;

/**
 * Created by lvjiawei on 2017/4/25.
 */
public interface OrdersDao extends BaseDao{
    int getLastId();
    List getOrderByUsername(String username);
    OrdersEntity getOrderByID(int id);
    Integer getTotalOrderAmount();
    Integer getUserOrderAmount(String username);
    boolean destroyOrder(int id);
    List getAllOrder();
    List getOrderById_list(int id);
    List statistic(String method);

}
