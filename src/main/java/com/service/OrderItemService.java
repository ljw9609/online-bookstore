package com.service;

import java.util.List;

/**
 * Created by lvjiawei on 2017/5/8.
 */
public interface OrderItemService {
    List query(String hql);
    boolean save(Object obj);
    boolean update(Object obj);
}
