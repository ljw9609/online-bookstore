package com.service;

import com.dao.*;
import com.entity.BooksEntity;
import com.entity.CategoryEntity;
import com.entity.UsersEntity;
import com.util.HibernateUtil;
import net.sf.json.JSONArray;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvjiawei on 2017/6/24.
 */
@Service
public class StatisticServiceImp implements StatisticService {
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

    @Qualifier("CategoryDao")
    @Autowired
    private CategoryDao categorydao;
    public void setCategorydao(CategoryDao categorydao){this.categorydao = categorydao;}

    public List<String> getUserList(){
        try{
            List l = userdao.getUsers();
            List<String> list2 = new ArrayList<>();
            for(int i = 1;i < l.size();i++){
                list2.add(((UsersEntity)l.get(i)).getUsername());
            }
            return list2;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getYearList(){
        try{
            List<String> list3 = new ArrayList<>();
            list3.add("2015");
            list3.add("2016");
            list3.add("2017");
            return list3;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getCategoryList(){
        try{
            List list6 = categorydao.getCategory();
            List<String> list7 = new ArrayList<>();
            for(int i = 0;i < list6.size();i++){
                list7.add(((CategoryEntity)list6.get(i)).getName());
            }
            return list7;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getBooknameList(){
        try{
            List list4 = bookdao.getBook();
            List<String> list5 = new ArrayList<>();
            for(int i = 0;i < list4.size();i++){
                list5.add(((BooksEntity)list4.get(i)).getTitle());
            }
            return list5;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String show_second(HttpServletResponse response, String method) throws IOException{
        PrintWriter out = response.getWriter();

        if(method.equals("user")){
            List<String> list2 = getUserList();
            JSONArray jsonarray = JSONArray.fromObject(list2);
            System.out.println(jsonarray);
            out.write(jsonarray.toString());
            out.flush();
            out.close();
        }
        if(method.equals("year")){
            List<String> list3 = getYearList();
            JSONArray jsonarray2 = JSONArray.fromObject(list3);
            System.out.println(jsonarray2);
            out.write(jsonarray2.toString());
            out.flush();
            out.close();
        }
        if(method.equals("bookname")){
            List<String> list5 = getBooknameList();
            JSONArray jsonarray2 = JSONArray.fromObject(list5);
            System.out.println(jsonarray2);
            out.write(jsonarray2.toString());
            out.flush();
            out.close();
        }
        if(method.equals("category")){
            List<String> list7 = getCategoryList();
            JSONArray jsonarray3 = JSONArray.fromObject(list7);
            out.write(jsonarray3.toString());
            out.flush();
            out.close();
        }
        return null;
    }

    public String show_bookinfo(HttpServletResponse response,String title) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println(title);
        List list = bookdao.getBookByTitle(title);
        BooksEntity book = (BooksEntity) list.get(0);

        JSONArray jsonarr = JSONArray.fromObject(list);
        System.out.println(jsonarr);
        out.write(jsonarr.toString());
        out.flush();
        out.close();

        return null;
    }

    public Double statistic_by(String param){
        try{
            List<String> userlist = getUserList();
            List<String> yearlist = getYearList();
            List<String> categorylist = getCategoryList();
            List<String> booknamelist = getBooknameList();

            List list = new ArrayList();
            if(yearlist.contains(param)){
                int year = Integer.valueOf(param);
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                Query query = session.createQuery("select sum(total_price) from OrdersEntity where year(orderdate) = "+year+"");
                list = query.list();
                session.getTransaction().commit();
            }
            else if(booknamelist.contains(param)){
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                int bookid = (int)session.createQuery("select id from BooksEntity where title = ?").setParameter(0,param).list().get(0);
                System.out.println("bookid:"+bookid);
                list = session.createQuery("select sum(price) from OrderItemEntity where book_id = "+bookid+"").list();
                session.getTransaction().commit();
            }
            else if(categorylist.contains(param)){
                int categoryid = categorydao.getIdByName(param);
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                String sql = "select sum(c.price) from orderitem as c join books as b on c.book_id = b.id where b.categoryid = "+categoryid+"";
                Query query = session.createSQLQuery(sql);
                list = query.list();
                session.getTransaction().commit();
            }
            else if(userlist.contains(param)){
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                Query query = session.createQuery("select sum(total_price) from OrdersEntity where username = ?");
                query.setParameter(0,param);
                list = query.list();
                session.getTransaction().commit();
            }

            if(list.size() > 0){
                Double result = (Double)list.get(0);
                return result;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String statistic(HttpServletResponse response,String param) throws IOException{
        PrintWriter out = response.getWriter();
        System.out.println(param);

        Double result = statistic_by(param);

        out.println("<p>" + param +" total sales: "+ result + "<p>");
        out.flush();
        out.close();

        return null;
    }
}
