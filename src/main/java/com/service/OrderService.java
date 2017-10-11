package com.service;

import com.entity.OrdersEntity;

import java.util.List;

/**
 * Created by lvjiawei on 2017/5/8.
 */
public interface OrderService {
    List query(String hql);
    boolean save(Object obj);
    boolean update(Object obj);
    int getLastId();
    List getOrderByUsername(String username);
    OrdersEntity getOrderByID(int id);
    Integer getTotalOrderAmount();
    Integer getUserOrderAmount(String username);
    boolean destroyOrder(int id);
    List getAllOrder();
    List getOrderById_list(int id);
    List statistic(String method);
    Double saleby(String method);
}
