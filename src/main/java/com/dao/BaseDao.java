package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.util.HibernateUtil;
import com.entity.*;

/**
 * Created by lvjiawei on 2017/4/17.
 */
public interface BaseDao {
    List query(String hql);
    boolean save(Object obj);
    boolean update(Object obj);
}
