package com.entity;

/**
 * Created by lvjiawei on 2017/5/6.
 */
public class tempitem {
    private int order_id;
    private String title;
    private int amount;
    private Double price;

    public tempitem(){

    }

    public int getOrder_id(){return order_id;}

    public void setOrder_id(int order_id){this.order_id = order_id;}

    public String getTitle(){return title;}

    public void setTitle(String title){this.title = title;}

    public int getAmount(){return amount;}

    public void setAmount(int amount){this.amount = amount;}

    public Double getPrice(){return price;}

    public void setPrice(Double price){this.price = price;}

}
