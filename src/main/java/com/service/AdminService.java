package com.service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by lvjiawei on 2017/6/24.
 */
public interface AdminService {
    String admin_user(HttpServletRequest request);
    String delete_user(HttpServletRequest request,int userid);
    String edit_user_show(HttpServletRequest request,String username);
    String edit_user(HttpServletRequest request,String username,String oldpassword,
                     String newpassword,String email);
    String add_user(HttpServletRequest request,String username,String password,
                    String confirmpassword,String email);

    String admin_book(HttpServletRequest request);
    String delete_book(HttpServletRequest request,int bookid);
    String edit_book_show(HttpServletRequest request,int bookid);
    String edit_book(HttpServletRequest request,String title,String author,String press,
                     String isbn,Double price,int amount,int categoryid,String intro);
    String add_book(HttpServletRequest request,String title,String author,String press,
                    String isbn,Double price,int amount,int categoryid,String intro);

    String admin_order(HttpServletRequest request);
    String delete_order(HttpServletRequest request,int orderid);
    String edit_order_show(HttpServletRequest request,int orderid);
    String edit_order(HttpServletRequest request,int orderid,String username,String receiver,String address,
                      String mobile,Double total_price,Timestamp orderdate);

    String admin_homepage(HttpServletRequest request);
    int getUserAmt();
    int getBookAmt();
    int getOrderAmt();
}
