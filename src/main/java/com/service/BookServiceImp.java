package com.service;

import com.dao.BooksDao;
import com.entity.BooksEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.util.HibernateUtil;
import com.util.MongoDBUtil;
import org.apache.struts2.ServletActionContext;
import org.bson.types.ObjectId;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by lvjiawei on 2017/5/8.
 */
@Service
public class BookServiceImp implements BookService{
    @Qualifier("BooksDao")
    @Autowired
    private BooksDao bookdao;

    @Autowired
    private MongoDBUtil mongo;

    public void setBookdao(BooksDao bookdao){this.bookdao = bookdao;}

    public List query(String hql){return bookdao.query(hql);}

    public boolean save(Object obj){return bookdao.save(obj);}

    public boolean update(Object obj){return bookdao.update(obj);}

    public BooksEntity getBookById(int id){return bookdao.getBookById(id);}

    public List getBookListById(int id){return bookdao.getBookListByID(id);}

    public List getBookByTitle(String title){return bookdao.getBookByTitle(title);}

    public List search(String keyword){return bookdao.search(keyword);}

    public Integer getTotalBookAmount(){return bookdao.getTotalBookAmount();}

    public boolean destroyBook(int id){return bookdao.destroyBook(id);}

    public List getBook(){return bookdao.getBook();}

    public boolean insertdata(String isbn,String title,String author,String press,Double price,int amount,int categoryid,String intro,String profile_id){
        return bookdao.insertdata(isbn, title, author, press, price, amount, categoryid,intro,profile_id);}

    public String show_detail(HttpServletRequest request,int bookid){
        List list = bookdao.getBookListByID(bookid);
        BooksEntity b = (BooksEntity)list.get(0);
        BooksEntity book = getBookProfile(b);

        if(list != null && list.size()>0){
            request.setAttribute("list", book);
        }
        return "success";
    }

    public String Search(HttpServletRequest request,String keyword){
        List list = bookdao.search(keyword);

        if(list != null && list.size()>0){
            request.setAttribute("list", list);
        }
        return "success";
    }

    public String getBookListByCategory(HttpServletRequest request,String categoryid){
        try{
            int id = Integer.parseInt(categoryid);
            List book = bookdao.getBookByCategoryID(id);
            if(book != null && book.size()>0){
                request.setAttribute("list", book);
            }
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateBookProfile(BooksEntity book){
        try {
            //UsersEntity u = userdao.getUserByID(user.getId());
            BooksEntity u = bookdao.getBookById(book.getId());
            //u.setName(user.getName());												// update name
            DBCollection collection = mongo.getDB().getCollection("bookprofile");	// get collection of mongoDB
            BasicDBObject search = new BasicDBObject();								// search Object
            if(u.getProfile_id().equals("")) {										// if the user do not has profileID, insert it into mongo and get profileID
                collection.insert(search);
                u.setProfile_id(search.get("_id").toString());
            } else {																// or get the search object
                search.put("_id", new ObjectId(u.getProfile_id()));
            }

            BasicDBObject updateObject = new BasicDBObject();
            String[] field  = {"Image_id"};
            for(int i = 0; i < field.length; ++i) {
                Method m = BooksEntity.class.getMethod("get" + field[i]);
                Object tmp = m.invoke(book);
                if(tmp != null && !tmp.equals(""))
                    updateObject.append(field[i].toLowerCase(), tmp.toString());
            }

            //BasicDBObject updateQuery = new BasicDBObject();						// create the update object
            //updateQuery.append("$set", updateObject);
            collection.update(search, updateObject);									// update profile in mongoDB
            bookdao.update(u);													// update user in mysql
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public BooksEntity getBookProfile(BooksEntity book){
        try {
           // UsersEntity u = userdao.getUserByID(user.getId());
            BooksEntity u = bookdao.getBookById(book.getId());
            if(u.getProfile_id().equals(""))											// the user has no profile in mongoDB
                return u;
            DBCollection collection = mongo.getDB().getCollection("bookprofile");	// get collection of mongoDB
            BasicDBObject search = new BasicDBObject();								// search Object
            search.append("_id", new ObjectId(u.getProfile_id()));
            DBObject profile = collection.findOne(search);
            String[] field  = {"Image_id"};
            for(int i = 0; i < field.length; ++i) {
                if(profile.containsField(field[i].toLowerCase())) {
                    Method m = u.getClass().getMethod("set" + field[i], String.class);
                    m.invoke(u, profile.get(field[i].toLowerCase()));
                }
            }
            return u;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
}
