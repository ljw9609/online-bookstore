package com.actions;

import com.entity.BooksEntity;
import com.entity.OrdersEntity;
import com.entity.UsersEntity;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.opensymphony.xwork2.ActionContext;
import com.service.*;
import com.util.*;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by lvjiawei on 2017/5/9.
 */
@Controller
public class AdminActions {
    @Qualifier("MongoDBUtil")
    @Autowired
    private MongoDBUtil mongo;

    @Qualifier("AdminService")
    @Autowired
    private AdminService adminservice;
    public void setAdminservice(AdminService adminservice){this.adminservice = adminservice;}

    @Qualifier("UserService")
    @Autowired
    private UserService userservice;
    public void setUserservice(UserService userservice){this.userservice = userservice;}

    @Qualifier("BookService")
    @Autowired
    private BookService bookservice;
    public void setBookservice(BookService bookservice){this.bookservice = bookservice;}

    private int userid;

    public int getUserid(){return userid;}

    public void setUserid(int userid){this.userid = userid;}

    private String username;

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    private String email;

    public String getEmail(){return email;}

    public void setEmail(String email){this.email = email;}

    private String oldpassword;

    public String getOldpassword(){return oldpassword;}

    public void setOldpassword(String oldpassword){this.oldpassword = oldpassword;}

    private String newpassword;

    public String getNewpassword(){return newpassword;}

    public void setNewpassword(String newpassword){this.newpassword = newpassword;}

    private String password;
    private String confirmpassword;

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

    public String getConfirmpassword(){return confirmpassword;}

    public void setConfirmpassword(String confirmpassword){this.confirmpassword = confirmpassword;}

    private int bookid;
    private String title;
    private String isbn;
    private Double price;
    private int amount;
    private String press;
    private String author;
    private String intro;
    private int categoryid;

    public int getBookid(){return bookid;}

    public void setBookid(int bookid){this.bookid = bookid;}

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public String getPress(){
        return press;
    }

    public void setPress(String press){
        this.press = press;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getIntro(){
        return intro;
    }

    public void setIntro(String intro){
        this.intro = intro;
    }

    public int getCategoryid(){return categoryid;}

    public void setCategoryid(int categoryid){this.categoryid = categoryid;}

    private int orderid;
    private String receiver;
    private String address;
    private String mobile;
    private Double total_price;
    private Timestamp orderdate;

    public int getOrderid(){return orderid;}

    public void setOrderid(int orderid){this.orderid = orderid;}

    public String getReceiver(){return receiver;}

    public void setReceiver(String receiver){this.receiver = receiver;}

    public String getAddress(){return address;}

    public void setAddress(String address){this.address = address;}

    public String getMobile(){return mobile;}

    public void setMobile(String mobile){this.mobile = mobile;}

    public Double getTotal_price(){return total_price;}

    public void setTotal_price(Double total_price){this.total_price = total_price;}

    public Timestamp getOrderdate(){return orderdate;}

    public void setOrderdate(Timestamp orderdate){this.orderdate = orderdate;}

    private int useramount;
    private int bookamount;
    private int orderamount;

    public int getUseramount(){return useramount;}

    public void setUseramount(int useramount){this.useramount = useramount;}

    public int getBookamount(){return bookamount;}

    public void setBookamount(int bookamount){this.bookamount = bookamount;}

    public int getOrderamount(){return orderamount;}

    public void setOrderamount(int orderamount){this.orderamount = orderamount;}

    private String filename;
    private File myFile;
    private String myFileContentType;
    private String myFileFileName;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    public String getMyFileContentType() {
        return myFileContentType;
    }

    public MongoDBUtil getMongo() {
        return mongo;
    }

    public void setMongo(MongoDBUtil mongo) {
        this.mongo = mongo;
    }

    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    public String getMyFileFileName() {
        return myFileFileName;
    }

    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    public String adminuser(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return adminservice.admin_user(request);
    }

    public String deleteuser(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return adminservice.delete_user(request,getUserid());
    }

    public String edituser_show(){
        HttpServletRequest request = ServletActionContext.getRequest();
        //return adminservice.edit_user_show(request,getUsername());
        UsersEntity u = userservice.getUserByUsername(getUsername());
        UsersEntity user = userservice.getUserProfile(u);
        ActionContext.getContext().put("userprofile",user);
        return "success";
    }

    public String edituser(){
        HttpServletRequest request = ServletActionContext.getRequest();
        //return adminservice.edit_user(request,getUsername(),getOldpassword(),getNewpassword(),getEmail());
        try {
            if (adminservice.edit_user(request, getUsername(), getOldpassword(), getNewpassword(), getEmail()).equals("success")){
                if(myFile == null){
                    return "success";
                }
                Date dt = new Date();
                UsersEntity u = userservice.getUserByUsername(getUsername());
                String newFileName = u.getUsername() + "-" + dt.getTime() + myFileFileName;

                DB db = mongo.getMongoClient().getDB("bookstore");
                GridFS gfsPhoto = new GridFS(db, "image");
                GridFSInputFile gfsFile = gfsPhoto.createFile(myFile);        // get image file from local drive
                gfsFile.setFilename(newFileName);                            // set a new filename for identify purpose
                gfsFile.save();
                u.setImage_id(gfsFile.getId().toString());
                userservice.updateUserProfile(u);
                return "success";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "fail";
    }

    public String adduser(){
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            //return adminservice.add_user(request,getUsername(),getPassword(),getConfirmpassword(),getEmail());
            if (adminservice.add_user(request, getUsername(), getPassword(), getConfirmpassword(), getEmail()).equals("success")) {
                if(myFile == null){
                    return "success";
                }
                Date dt = new Date();
                UsersEntity u = userservice.getUserByUsername(getUsername());
                String newFileName = u.getUsername() + "-" + dt.getTime() + myFileFileName;

                DB db = mongo.getMongoClient().getDB("bookstore");
                GridFS gfsPhoto = new GridFS(db, "image");
                GridFSInputFile gfsFile = gfsPhoto.createFile(myFile);        // get image file from local drive
                gfsFile.setFilename(newFileName);                            // set a new filename for identify purpose
                gfsFile.save();
                u.setImage_id(gfsFile.getId().toString());
                userservice.updateUserProfile(u);
                return "success";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "fail";
    }

    public String adminbook(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return adminservice.admin_book(request);
    }

    public String deletebook(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return adminservice.delete_book(request,getBookid());
    }

    public String editbook_show(){
        HttpServletRequest request = ServletActionContext.getRequest();
        BooksEntity b = bookservice.getBookById(getBookid());
        BooksEntity book = bookservice.getBookProfile(b);
        ActionContext.getContext().put("bookprofile",book);
        return "success";
    }

    public String editbook(){
        HttpServletRequest request = ServletActionContext.getRequest();
        //return adminservice.edit_book(request,getTitle(),getAuthor(),getPress(),getIsbn(),getPrice(),
                //getAmount(),getCategoryid(),getIntro());
        try{
            if (adminservice.edit_book(request,getTitle(),getAuthor(),getPress(),getIsbn(),getPrice(),
                    getAmount(),getCategoryid(),getIntro()).equals("success")) {
                if(myFile == null){
                    return "success";
                }
                Date dt = new Date();
                BooksEntity u = (BooksEntity) bookservice.getBookByTitle(getTitle()).get(0);
                String newFileName = u.getTitle() + "-" + dt.getTime() + myFileFileName;

                DB db = mongo.getMongoClient().getDB("bookstore");
                GridFS gfsPhoto = new GridFS(db, "image");
                GridFSInputFile gfsFile = gfsPhoto.createFile(myFile);        // get image file from local drive
                gfsFile.setFilename(newFileName);                            // set a new filename for identify purpose
                gfsFile.save();
                u.setImage_id(gfsFile.getId().toString());
                bookservice.updateBookProfile(u);
                return "success";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "fail";
    }

    public String addbook(){
        HttpServletRequest request = ServletActionContext.getRequest();
        //return adminservice.add_book(request,getTitle(),getAuthor(),getPress(),getIsbn(),
                //getPrice(),getAmount(),getCategoryid(),getIntro());
        try{
            if(adminservice.add_book(request,getTitle(),getAuthor(),getPress(),getIsbn(),
                    getPrice(),getAmount(),getCategoryid(),getIntro()).equals("success")){
                if(myFile == null){
                    return "success";
                }
                Date dt = new Date();
                BooksEntity u = (BooksEntity)bookservice.getBookByTitle(getTitle()).get(0);
                String newFileName = u.getTitle() + "-" + dt.getTime() + myFileFileName;

                DB db = mongo.getMongoClient().getDB("bookstore");
                GridFS gfsPhoto = new GridFS(db, "image");
                GridFSInputFile gfsFile = gfsPhoto.createFile(myFile);        // get image file from local drive
                gfsFile.setFilename(newFileName);                            // set a new filename for identify purpose
                gfsFile.save();
                u.setImage_id(gfsFile.getId().toString());
                bookservice.updateBookProfile(u);
                return "success";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "fails";
    }

    public String adminorder(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return adminservice.admin_order(request);
    }

    public String deleteorder(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return adminservice.delete_order(request,getOrderid());
    }

    public String editorder_show(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return adminservice.edit_order_show(request,getOrderid());
    }

    public String editorder(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return adminservice.edit_order(request,getOrderid(),getUsername(),getReceiver(),getAddress(),
                getMobile(),getTotal_price(),getOrderdate());
    }

    public String admin(){
        HttpServletRequest request = ServletActionContext.getRequest();
        setUseramount(adminservice.getUserAmt());
        setBookamount(adminservice.getBookAmt());
        setOrderamount(adminservice.getOrderAmt());
        return adminservice.admin_homepage(request);
    }

    public String index(){
        int [] a = new int[]{-1,-1,-1};
        Random ran = new Random();
        int cnt = 0;
        while(cnt < a.length){
            boolean flag = true;
            int r = ran.nextInt(4) + 1;
            for(int i = 0;i < a.length;i++){
                if(r == a[i]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                a[cnt] = r;
                cnt++;
            }
        }

        List booklist = new ArrayList();
        for(int i = 0;i < a.length;i++){
            BooksEntity b = bookservice.getBookById(a[i]);
            booklist.add(bookservice.getBookProfile(b));
        }
        ActionContext.getContext().put("booklist",booklist);
        return "index";
    }
}
