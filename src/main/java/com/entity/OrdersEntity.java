package com.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lvjiawei on 2017/4/25.
 */
public class OrdersEntity {
    private int id;
    private String username;
    private String receiver;
    private String mobile;
    private String address;
    private Double total_price;
    private Timestamp orderdate;

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getReceiver(){return receiver;}

    public void setReceiver(String receiver){this.receiver = receiver;}

    public String getMobile(){return mobile;}

    public void setMobile(String mobile){this.mobile = mobile;}

    public String getAddress(){return address;}

    public void setAddress(String address){this.address = address;}

    public Double getTotal_price(){return total_price;}

    public void setTotal_price(Double total_price){this.total_price = total_price;}

    public Timestamp getOrderdate(){return orderdate;}

    public void setOrderdate(Timestamp orderdate){this.orderdate = orderdate;}

    public OrdersEntity(){}

    private Set participants = new HashSet();
    public Set getParticipants() { return participants; }
    public void setParticipants(Set participants) {
        this.participants = participants;
    }

    private Set<OrderItemEntity> orderitems = new HashSet<OrderItemEntity>();

    public Set<OrderItemEntity> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(Set<OrderItemEntity> orderitems) {
        this.orderitems = orderitems;
    }
}
