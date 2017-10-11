package com.actions;

import com.entity.BooksEntity;
import com.entity.OrdersEntity;
import com.entity.UsersEntity;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BookService;
import com.service.OrderService;
import com.service.StatisticService;
import com.service.UserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import net.sf.json.*;
/**
 * Created by lvjiawei on 2017/5/23.
 */
@Controller
public class testAjaxActions extends ActionSupport{
    @Qualifier("OrderService")
    @Autowired
    private OrderService orderservice;
    public void setOrderservice(OrderService orderservice){this.orderservice = orderservice;}

    @Qualifier("UserService")
    @Autowired
    private UserService userservice;
    public void setUserservice(UserService userservice){this.userservice = userservice;}

    @Qualifier("BookService")
    @Autowired
    private BookService bookservice;
    public void setBookservice(BookService bookservice){this.bookservice = bookservice;}

    @Qualifier("StatisticService")
    @Autowired
    private StatisticService statisticservice;
    public void setStatisticservice(StatisticService statisticservice){this.statisticservice = statisticservice;}

    private String method;
    public String getMethod(){return method;}

    public void setMethod(String method){this.method = method;}

    private String user;
    public String getUser(){return user;}

    public void setUser(String user){this.user = user;}

    private String title;
    public String getTitle(){return title;}

    public void setTitle(String title){this.title = title;}

    public String salebyuser() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        return statisticservice.statistic(response,getUser());
    }

    public String showsecond() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        return statisticservice.show_second(response,getMethod());
    }

    public String showbookinfo() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        return statisticservice.show_bookinfo(response,getTitle());
    }
}
