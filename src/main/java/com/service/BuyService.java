package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lvjiawei on 2017/6/24.
 */
public interface BuyService {
    boolean add_item(String bookid,String buyamount,HttpServletRequest request);
    boolean remove_item(String bookid,String buyamount,HttpServletRequest request);
    boolean empty_cart(HttpServletRequest request);
    boolean check_status(HttpServletRequest request);

    String cart_method(HttpServletRequest request, HttpServletResponse response,String bookid,
                       String amt,String action) throws IOException;
    String submit_order(HttpServletRequest request,HttpServletResponse response,String username,
                        String receiver,String address,String mobile);
    String get_order(HttpServletRequest request,String username);
    String show_item(HttpServletRequest request,String orderid);

}
