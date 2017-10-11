package com.dao;

import com.entity.BooksEntity;
import com.entity.CategoryEntity;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


import java.awt.print.Book;
import java.util.List;
import java.util.Set;

/**
 * Created by lvjiawei on 2017/5/8.
 */
@Repository
public class BooksDaoImp extends BaseDaoImp implements BooksDao {
    public BooksEntity getBookById(int id){
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            //UsersEntity user = (UsersEntity) session.load(UsersEntity.class, id);
            List l = session.createQuery("from BooksEntity where id=?").setParameter(0,id).list();
            session.getTransaction().commit();
            if(l.size() > 0){
                return (BooksEntity) l.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List getBookListByID(int id) {
        try {
            String hql = "from BooksEntity where id=?";
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List l = session.createQuery(hql).setParameter(0,id).list();
            session.getTransaction().commit();
            if( l.size() > 0 )
                return l;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List getBookByTitle(String title) {
        try {
            String hql = "from BooksEntity where title=?";
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List l = session.createQuery(hql).setParameter(0,title).list();
            session.getTransaction().commit();
            if( l.size() > 0 )
                return l;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List search(String keyword){
        try{
            String hql = "from BooksEntity where title like '%"+keyword+"%' or press like '%"+keyword+"%'";
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List list = session.createQuery(hql).list();
            session.getTransaction().commit();
            if(list.size() > 0)
                return list;
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public Integer getTotalBookAmount() {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "select count(*) from BooksEntity ";
            Long num = (Long) session.createQuery(hql).list().get(0);
            session.getTransaction().commit();
            return num.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean destroyBook(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            BooksEntity book = (BooksEntity) session.get(BooksEntity.class, id);
            session.delete(book);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List getBook(){
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List l = session.createQuery("from BooksEntity ").list();
            session.getTransaction().commit();
            if( l.size() > 0 ){
                System.out.println("success");
                System.out.println(l.size());
                return l;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertdata(String isbn,String title,String author,String press,Double price,int amount,int categoryid,String intro,String profile_id){
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            BooksEntity book = new BooksEntity();
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setAuthor(author);
            book.setPress(press);
            book.setPrice(price);
            book.setAmount(amount);
            book.setCategoryid(categoryid);
            book.setIntro(intro);
            book.setProfile_id("");

            session.save(book);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List getBookByCategoryID(int id){
        try{
            String hql = "from BooksEntity where categoryid = ?";
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List rs = session.createQuery(hql).setParameter(0, id).list();
            session.getTransaction().commit();
            if(rs.size() == 0)
                return null;
            return rs;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
