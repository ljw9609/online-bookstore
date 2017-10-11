package com.actions;


import java.util.*;

import com.entity.*;
import com.service.BookService;
import org.hibernate.Session;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;
import com.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * Created by lvjiawei on 2017/5/5.
 */
@Controller
public class BookActions extends ActionSupport {
    @Qualifier("BookService")
    @Autowired
    private BookService bookservice;
    public void setBookservice(BookService bookservice){this.bookservice = bookservice;}

    private String type;
    private int bookid;
    private String string_bookid;
    private String keyword;

    private String catelog;

    public String getType(){return type;}

    public void setType(String type){this.type = type;}

    public int getBookid(){return bookid;}

    public void setBookid(int bookid){this.bookid = bookid;}

    public String getCatelog(){return catelog;}

    public void setCatelog(String catelog){this.catelog = catelog;}

    public String getString_bookid(){return string_bookid;}

    public void setString_bookid(String string_bookid){this.string_bookid = string_bookid;}

    public String getKeyword(){return keyword;}

    public void setKeyword(String keyword){this.keyword = keyword;}

/*
    public String catelog(){
        HttpServletRequest request = ServletActionContext.getRequest();

        switch (getType()){
            case "1":
                setCatelog("计算机图书");
                break;
            case "2":
                setCatelog("文学类图书");
                break;
            case "3":
                setCatelog("教辅类图书");
                break;
            default:
                break;
        }

        int id = Integer.parseInt(getType());
        List list = bookservice.getBookListById(id);
        if(list != null && list.size()>0){
            request.setAttribute("list", list);
        }
        return SUCCESS;

    }*/

    public String catelog(){
        HttpServletRequest request = ServletActionContext.getRequest();

        switch (getType()){
            case "1":
                setCatelog("计算机图书");
                break;
            case "2":
                setCatelog("文学类图书");
                break;
            case "3":
                setCatelog("教辅类图书");
                break;
            default:
                break;
        }

        return bookservice.getBookListByCategory(request,getType());
    }

    public String show_detail(){
        HttpServletRequest request = ServletActionContext.getRequest();
        //setString_bookid(String.valueOf(getBookid()));
        return bookservice.show_detail(request,getBookid());
    }

    public String search(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return bookservice.Search(request,getKeyword());
    }
}
