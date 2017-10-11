package com.dao;
import com.entity.*;
import java.util.*;

/**
 * Created by lvjiawei on 2017/5/4.
 */
public class Cart {
    private HashMap<BooksEntity, Integer> cartItems = null;

    public HashMap<BooksEntity, Integer> getCartItems() {
        return cartItems;
    }

    private double totalPrice = 0;

    public double getTotalPrice() {
        return totalPrice;
    }

    public Cart() {
        cartItems = new HashMap<BooksEntity, Integer>();
        totalPrice = 0;
    }

    public boolean addItem(BooksEntity b, Integer amount){
        if (cartItems == null){
            System.out.println("cartitem null");
            cartItems = new HashMap<BooksEntity, Integer>();
        }
        Integer amt = cartItems.get(b);
        System.out.println("old amt "+amt+"");
        if (cartItems.containsKey(b)){
            System.out.println("contains");
            amt += amount;
            totalPrice += b.getPrice()*amount;
            cartItems.put(b,amt);
        }
        else {
            System.out.println("not contain");
            totalPrice += b.getPrice()*amount;
            cartItems.put(b,amount);
            System.out.println("amount "+amount+"");
        }
        return true;
    }

    public boolean removeItem(BooksEntity b, Integer amount){
        if (cartItems == null){
            return false;
        }
        if (cartItems.containsKey(b)){
            Integer amt = cartItems.get(b);
            if (amt > amount){
                amt -= amount;
                totalPrice -= b.getPrice()*amount;
                cartItems.put(b,amt);
            }
            else{
                totalPrice -=b.getPrice()*amt;
                cartItems.remove(b);
            }
        }
        else {
            return false;
        }
        return true;
    }

    public void printItemList(){
        Set<BooksEntity> booklist = cartItems.keySet();
        Collection<Integer> amountlist = cartItems.values();
        Iterator<Integer> ait = amountlist.iterator();
        Iterator<BooksEntity> bit = booklist.iterator();
        while (bit.hasNext()){
            BooksEntity b = bit.next();
            Integer amt = ait.next();
            System.out.println(b.getTitle());
            System.out.println(amt);
        }
    }
}
