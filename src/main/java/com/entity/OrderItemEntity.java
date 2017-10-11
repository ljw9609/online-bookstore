package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lvjiawei on 2017/4/25.
 */
public class OrderItemEntity {
    private int id;
    private int order_id;
    private int book_id;
    private int amount;
    private Double price;


    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public int getOrder_id(){return order_id;}

    public void setOrder_id(int order_id){this.order_id = order_id;}

    public int getBook_id(){return book_id;}

    public void setBook_id(int book_id){this.book_id = book_id;}

    public int getAmount(){return amount;}

    public void setAmount(int amount){this.amount = amount;}

    public Double getPrice(){return price;}

    public void setPrice(Double price){this.price = price;}

    public OrderItemEntity(){}

    private Set participants = new HashSet();
    public Set getParticipants() { return participants; }
    public void setParticipants(Set participants) {
        this.participants = participants;
    }

    private OrdersEntity order;

    public OrdersEntity getOrder() {
        return order;
    }

    public void setOrder(OrdersEntity order) {
        this.order = order;
    }
}
