package com.service;

import com.dao.OrderItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lvjiawei on 2017/5/8.
 */
@Service
public class OrderItemServiceImp implements OrderItemService {
    @Qualifier("OrderItemDao")
    @Autowired
    private OrderItemDao orderitemdao;

    public void setOrderitemdao(OrderItemDao orderitemdao){this.orderitemdao = orderitemdao;}

    public List query(String hql){return orderitemdao.query(hql);}

    public boolean save(Object obj){return orderitemdao.save(obj);}

    public boolean update(Object obj){return orderitemdao.update(obj);}
}
