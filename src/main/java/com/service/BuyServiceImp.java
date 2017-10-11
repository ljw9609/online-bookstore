package com.service;

import com.dao.BooksDao;
import com.dao.Cart;
import com.dao.OrderItemDao;
import com.dao.OrdersDao;
import com.entity.BooksEntity;
import com.entity.OrderItemEntity;
import com.entity.OrdersEntity;
import com.entity.tempitem;
import com.util.HibernateUtil;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by lvjiawei on 2017/6/24.
 */
@Service
public class BuyServiceImp implements BuyService {
    @Qualifier("BooksDao")
    @Autowired
    private BooksDao bookdao;
    public void setBookdao(BooksDao bookdao){this.bookdao = bookdao;}

    @Qualifier("OrdersDao")
    @Autowired
    private OrdersDao orderdao;
    public void setOrderdao(OrdersDao orderdao){this.orderdao = orderdao;}

    @Qualifier("OrderItemDao")
    @Autowired
    private OrderItemDao orderitemdao;
    public void setOrderitemdao(OrderItemDao orderitemdao){this.orderitemdao = orderitemdao;}

    public boolean add_item(String bookid,String buyamount,HttpServletRequest request){
        Cart c = null;
        if (request.getSession().getAttribute("cart") == null){
            c = new Cart();
        }
        else{
            c =(Cart) request.getSession().getAttribute("cart");
            System.out.println("has cart");
        }

        int id = Integer.parseInt(bookid);
        System.out.println("bookid "+id+"");
        BooksEntity b =  bookdao.getBookById(id);
        int total_amount = b.getAmount();
        if (b != null){
            Integer amount = new Integer(buyamount);
            if(amount > total_amount){
                return false;
            }else{
                c.addItem(b, amount);
            }
        }
        request.getSession().setAttribute("cart",c);
        return true;
    }

    public boolean remove_item(String bookid,String buyamount,HttpServletRequest request){
        Cart c =null;
        if (request.getSession().getAttribute("cart") == null){
            return false;
        }
        else{
            c =(Cart) request.getSession().getAttribute("cart");
        }
        int id = Integer.parseInt(bookid);
        BooksEntity b =  bookdao.getBookById(id);
        if (b != null){
            Integer amount = new Integer(buyamount);
            c.removeItem(b,amount);
        }
        request.getSession().setAttribute("cart",c);
        return true;
    }

    public boolean empty_cart(HttpServletRequest request){
        request.getSession().removeAttribute("cart");
        return true;
    }

    public boolean check_status(HttpServletRequest request){
        if(request.getSession().getAttribute("username") != null){
            return true;
        }else{
            return false;
        }
    }

    public String cart_method(HttpServletRequest request, HttpServletResponse response, String bookid,
                              String amt, String action) throws IOException{
        if (action != null){
            if (action.equals("add")){
                if(add_item(bookid, amt, request)){
                    return "success";
                }
                else{
                    PrintWriter out = response.getWriter();
                    out.println("<html lang=\"en\">");
                    out.println("<head>");
                    out.println("<meta charset=\"utf-8\">");
                    out.println("<title>Error!</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<p>Over Stock amount!</p>");
                    out.println("</body>");
                    out.println("</html>");
                    //request.setAttribute("tipMessage","超过库存");
                }
            }

            if (action.equals("remove")){
                if(remove_item(bookid, amt, request)){
                    return "success";
                }
            }

            if (action.equals("removeone")){
                if (remove_item(bookid, "1", request)){
                    return "success";
                }
            }

            if (action.equals("addone")){
                if (add_item(bookid, "1", request)){
                    return "success";
                }
            }

            if (action.equals("empty")){
                if (empty_cart(request)){
                    return "success";
                }
            }
            if (action.equals("pay")){
                if(check_status(request)){
                    return "order";
                }else{
                    PrintWriter out = response.getWriter();
                    out.println("<html lang=\"en\">");
                    out.println("<head>");
                    out.println("<meta charset=\"utf-8\">");
                    out.println("<title>Error!</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<p>Please log in!</p>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }

        }
        return null;
    }

    public String submit_order(HttpServletRequest request,HttpServletResponse response,String username,
                               String receiver,String address,String mobile){
        Cart c = null;
        if (request.getSession().getAttribute("cart") == null){
            c = new Cart();
        }
        else{
            c =(Cart) request.getSession().getAttribute("cart");
            System.out.println("has cart");
        }

        OrdersEntity order = new OrdersEntity();

        order.setReceiver(receiver);
        order.setMobile(mobile);
        order.setAddress(address);
        order.setUsername(username);
        order.setTotal_price(c.getTotalPrice());

        boolean rc = orderdao.save(order);
        if(rc){
            int last_id = orderdao.getLastId();
            System.out.println(last_id);
            HashMap<BooksEntity, Integer> itemlist = c.getCartItems();
            Set<BooksEntity> booklist = itemlist.keySet();
            Collection<Integer> amountlist = itemlist.values();
            Iterator<Integer> ait = amountlist.iterator();
            Iterator<BooksEntity> bit = booklist.iterator();
            while (ait.hasNext() && bit.hasNext()) {
                BooksEntity b = bit.next();
                Integer amt = ait.next();

                OrderItemEntity orderitem = new OrderItemEntity();

                orderitem.setOrder_id(last_id);
                orderitem.setBook_id(b.getId());
                orderitem.setAmount(amt);
                Double price = amt * b.getPrice();
                orderitem.setPrice(price);

                BooksEntity book = bookdao.getBookById(b.getId());
                book.setId(b.getId());
                book.setAmount(b.getAmount() - amt);

                boolean flag = orderitemdao.save(orderitem);
                boolean flag3 = bookdao.update(book);
                if(flag && flag3) System.out.println("succ*");

            }
            boolean rc2 = empty_cart(request);
            return "success";
        }else{
            return null;
        }
    }

    public String get_order(HttpServletRequest request,String username){
        List list = orderdao.getOrderByUsername(username);
        if(list != null && list.size()>0){
            request.setAttribute("list", list);
        }
        else{
            System.out.println("fail");
        }
        return "getordersuccess";
    }

    public String show_item(HttpServletRequest request,String orderid){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        int id = Integer.parseInt(orderid);
        List list = session.createQuery("select a.order_id,b.title,a.amount,a.price from OrderItemEntity as a,BooksEntity as b where a.order_id = "+id+" and a.book_id = b.id").list();
        session.getTransaction().commit();

        if(list != null && list.size()>0){
            List itemlist = new ArrayList();
            for(int i = 0;i < list.size();i++){
                tempitem temp = new tempitem();
                Object[] obj = (Object[])list.get(i);
                int order_id = (int)obj[0];
                String title = (String)obj[1];
                int amount = (int)obj[2];
                Double price = (Double)obj[3];

                temp.setOrder_id(order_id);
                temp.setTitle(title);
                temp.setAmount(amount);
                temp.setPrice(price);

                itemlist.add(temp);
            }
            request.setAttribute("itemlist", itemlist);
        }
        else{
            System.out.println("fail");
        }
        return "success";
    }

}
