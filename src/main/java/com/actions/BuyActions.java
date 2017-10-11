package com.actions;

import com.service.BuyService;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;


/**
 * Created by lvjiawei on 2017/4/25.
 */
@Controller
public class BuyActions extends ActionSupport {
    @Qualifier("BuyService")
    @Autowired
    private BuyService buyservice;
    public void setBuyservice(BuyService buyservice) {
        this.buyservice = buyservice;
    }

    private String bookid;
    private String action;
    private String buyamount;

    private String username;
    private String receiver;
    private String address;
    private String mobile;
    private String orderid;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBuyamount() {
        return buyamount;
    }

    public void setBuyamount(String buyamount) {
        this.buyamount = buyamount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String cart_method2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        return buyservice.cart_method(request, response, getBookid(), getBuyamount(), getAction());
    }

    public String submit_order2() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        return buyservice.submit_order(request, response, getUsername(), getReceiver(), getAddress(), getMobile());
    }

    public String getOrder() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return buyservice.get_order(request, getUsername());
    }

    public String show_item() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return buyservice.show_item(request, getOrderid());
    }
}
