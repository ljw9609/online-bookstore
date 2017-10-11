package com.service;

import com.dao.BooksDao;
import com.dao.OrderItemDao;
import com.dao.OrdersDao;
import com.dao.UsersDao;
import com.entity.BooksEntity;
import com.entity.OrdersEntity;
import com.entity.UsersEntity;

import com.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lvjiawei on 2017/6/24.
 */
@Service
public class AdminServiceImp implements AdminService {
    @Qualifier("UsersDao")
    @Autowired
    private UsersDao userdao;
    public void setUserdao(UsersDao userdao){this.userdao = userdao;}

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

    public String admin_user(HttpServletRequest request){
        List list = userdao.getUsers();

        if(list != null && list.size()>0){
            request.setAttribute("userlist", list);
        }
        return "success";
    }

    public String delete_user(HttpServletRequest request,int userid){
        boolean rc = userdao.destroyUser(userid);
        if(rc){
            List list = userdao.getUsers();
            if(list != null && list.size()>0){
                request.setAttribute("userlist", list);
            }
        }
        return "success";
    }

    public String edit_user_show(HttpServletRequest request,String username){
        List list = userdao.getUserInfo(username);
        if(list != null && list.size()>0){
            request.setAttribute("userlist", list);
        }
        return "success";
    }

    public String edit_user(HttpServletRequest request,String username,String oldpassword,
                            String newpassword,String email){
        UsersEntity user = userdao.getUserByUsername(username);
        int id = user.getId();

        if(userdao.checkUserExist(username,oldpassword)){
            user.setId(id);
            user.setUsername(username);
            user.setPassword(MD5Util.encode(newpassword));
            user.setConfirmpassword(MD5Util.encode(newpassword));
            user.setEmail(email);
            boolean flag = userdao.update(user);
            if(flag){
                request.setAttribute("tipMessage","Update success!");
                return "success";
            }
        }else{
            request.setAttribute("tipMessage","password error!");
            return "fail";
        }
        return "fail";
    }

    public String add_user(HttpServletRequest request,String username,String password,
                           String confirmpassword,String email){
        if(username.equals("")){
            request.setAttribute("tipMessage","username cannot be null");
            return "fail";
        }
        if(password.equals("")){
            request.setAttribute("tipMessage","password cannot be null");
            return "fail";
        }
        if(!password.equals(confirmpassword)){
            request.setAttribute("tipMessage","two password not match");
            return "fail";
        }

        if(userdao.hasUsernameDup(username)){
            request.setAttribute("tipMessage","username existed!");
            return "fail";
        }
        else {
            if (userdao.insertData(username, password, confirmpassword, email,"")) {
                request.setAttribute("tipMessage", "regist succeed");
                return "success";
            } else {
                request.setAttribute("tipMessage", "regist fail");
                return "fail";
            }
        }
    }

    public String admin_book(HttpServletRequest request){
        List list = bookdao.getBook();
        if(list != null && list.size()>0){
            request.setAttribute("booklist", list);
        }
        else{
            System.out.println("fail");
        }
        return "success";
    }

    public String delete_book(HttpServletRequest request,int bookid){
        boolean rc = bookdao.destroyBook(bookid);
        if(rc){
            List list = bookdao.getBook();
            if(list != null && list.size()>0){
                request.setAttribute("booklist", list);
            }
            else{
                System.out.println("fail");
            }
        }else{
            System.out.println("fail");
        }
        return "success";
    }

    public String edit_book_show(HttpServletRequest request,int bookid){
        List list = bookdao.getBookListByID(bookid);

        if(list != null && list.size()>0){
            request.setAttribute("booklist", list);
        }
        else{
            System.out.println("fail");
        }
        return "success";
    }

    public String edit_book(HttpServletRequest request,String title,String author,String press,
                     String isbn,Double price,int amount,int categoyrid,String intro){
        BooksEntity book = (BooksEntity) bookdao.getBookByTitle(title).get(0);
        int id = book.getId();

        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setAmount(amount);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setCategoryid(categoyrid);
        book.setIntro(intro);
        boolean rc = bookdao.update(book);
        if(rc){
            request.setAttribute("tipMessage","Update success!");
            return "success";
        }
        return "fail";
    }

    public String add_book(HttpServletRequest request,String title,String author,String press,
                           String isbn,Double price,int amount,int categoryid,String intro){
        if (bookdao.insertdata(isbn, title, author, press, price, amount, categoryid,intro,"")) {
            request.setAttribute("tipMessage", "add succeed");
            return "success";
        } else {
            request.setAttribute("tipMessage", "add fail");
            return "fail";
        }
    }

    public String admin_order(HttpServletRequest request){
        List list = orderdao.getAllOrder();
        if(list != null && list.size()>0){
            request.setAttribute("orderlist", list);
        }
        else{
            System.out.println("fail");
        }
        return "success";
    }

    public String delete_order(HttpServletRequest request,int orderid){
        boolean rc = orderdao.destroyOrder(orderid);
        if(rc){
            List list = orderdao.getAllOrder();
            if(list != null && list.size()>0){
                request.setAttribute("orderlist", list);
            }
            else{
                System.out.println("fail");
            }
        }else{
            System.out.println("fail");
        }
        return "success";
    }

    public String edit_order_show(HttpServletRequest request,int orderid){
        List list = orderdao.getOrderById_list(orderid);

        if(list != null && list.size()>0){
            request.setAttribute("orderlist", list);
        }
        else{
            System.out.println("fail");
        }

        return "success";
    }

    public String edit_order(HttpServletRequest request,int orderid,String username,String receiver,String address,
                             String mobile,Double total_price,Timestamp orderdate){
        OrdersEntity order = orderdao.getOrderByID(orderid);
        int id = order.getId();
        order.setId(id);
        order.setUsername(username);
        order.setAddress(address);
        order.setMobile(mobile);
        order.setReceiver(receiver);
        order.setTotal_price(total_price);
        order.setOrderdate(orderdate);

        boolean rc = orderdao.update(order);
        if(rc){
            request.setAttribute("tipMessage","Update success!");
            return "success";
        }
        return "fail";
    }

    public String admin_homepage(HttpServletRequest request){
        List userlist = userdao.getUsers();
        if(userlist.size() > 0) request.setAttribute("userlist",userlist);
        List booklist = bookdao.getBook();
        if(booklist.size() > 0) request.setAttribute("booklist",booklist);
        List orderlist = orderdao.getAllOrder();
        if(orderlist.size() > 0) request.setAttribute("orderlist",orderlist);
        return "success";
    }

    public int getUserAmt(){
        return userdao.getTotalUserAmount();
    }

    public int getBookAmt(){
        return bookdao.getTotalBookAmount();
    }

    public int getOrderAmt(){
        return orderdao.getTotalOrderAmount();
    }
}
