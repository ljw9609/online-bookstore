package com.dao;

import com.entity.BooksEntity;
import com.entity.UsersEntity;
import com.util.HibernateUtil;

import java.util.*;

import org.hibernate.Session;
import com.util.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 * Created by lvjiawei on 2017/4/25.
 */
public interface BooksDao extends BaseDao {
    BooksEntity getBookById(int id);
    List getBookListByID(int id);
    List getBookByTitle(String title);
    List search(String keyword);
    Integer getTotalBookAmount();
    boolean destroyBook(int id);
    List getBook();
    boolean insertdata(String isbn,String title,String author,String press,Double price,int amount,int categoryid,String intro,String profile_id);
    List getBookByCategoryID(int id);
}
