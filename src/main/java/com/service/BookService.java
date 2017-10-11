package com.service;

import java.awt.print.Book;
import java.util.List;
import com.entity.BooksEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lvjiawei on 2017/5/8.
 */
public interface BookService {
    List query(String hql);
    boolean save(Object obj);
    boolean update(Object obj);
    BooksEntity getBookById(int id);
    List getBookListById(int id);
    List getBookByTitle(String title);
    List search(String keyword);
    Integer getTotalBookAmount();
    boolean destroyBook(int id);
    List getBook();
    boolean insertdata(String isbn,String title,String author,String press,Double price,int amount,int categoryid,String intro,String profile_id);
    String show_detail(HttpServletRequest request,int bookid);
    String Search(HttpServletRequest request,String keyword);
    String getBookListByCategory(HttpServletRequest request,String categoryid);
    boolean updateBookProfile(BooksEntity book);
    BooksEntity getBookProfile(BooksEntity book);
}
