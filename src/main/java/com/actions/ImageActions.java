package com.actions;

import com.entity.BooksEntity;
import com.entity.UsersEntity;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BookService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.util.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * Created by lvjiawei on 2017/6/7.
 */
@Controller
public class ImageActions extends ActionSupport {
    private String filename;
    private File myFile;
    private String myFileContentType;
    private String myFileFileName;
    private Integer bookID;

    @Qualifier("MongoDBUtil")
    @Autowired
    private MongoDBUtil mongo;

    @Qualifier("UserService")
    @Autowired
    private UserService userservice;

    @Qualifier("BookService")
    @Autowired
    private BookService bookservice;

    public String viewImage() {
        if(filename == null || filename.equals(""))
            return null;
        try {
            DB db = mongo.getMongoClient().getDB("bookstore");
            GridFS gfsPhoto = new GridFS(db, "image");
            GridFSDBFile imageForOutput = gfsPhoto.findOne(filename);
            if(imageForOutput == null) {
                return null;
            }
            InputStream in = imageForOutput.getInputStream();
            ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
            byte[] bytes = new byte[1024];
            while( -1 != in.read(bytes))
                out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String viewImageByID() {
        System.out.println(filename);
        if(filename == null || filename.equals(""))
            return null;
        try {
            DB db = mongo.getMongoClient().getDB("bookstore");
            GridFS gfsPhoto = new GridFS(db, "image");
            BasicDBObject obj = new BasicDBObject().append("_id", new ObjectId(filename));
            GridFSDBFile imageForOutput = gfsPhoto.findOne(obj);
            System.out.println(imageForOutput);
            if(imageForOutput == null) {
                return null;
            }
            InputStream in = imageForOutput.getInputStream();
            ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
            byte[] bytes = new byte[1024];
            while( -1 != in.read(bytes))
                out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public String uploadUserImage() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();

            Date dt = new Date();
            //UsersEntity u = (UsersEntity) ActionContext.getContext().getSession().get("user");
            UsersEntity u = (UsersEntity) request.getSession().getAttribute("user");

            u = userservice.getUserByUsername(u.getUsername());
            String newFileName = u.getUsername() + "-" + dt.getTime() + myFileFileName;

            DB db = mongo.getMongoClient().getDB("bookstore");
            GridFS gfsPhoto = new GridFS(db, "image");
            GridFSInputFile gfsFile = gfsPhoto.createFile(myFile);		// get image file from local drive
            gfsFile.setFilename(newFileName);							// set a new filename for identify purpose
            gfsFile.save();
            u.setImage_id(gfsFile.getId().toString());
            userservice.updateUserProfile(u);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }


    /*getters and setters*/

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

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setUserservice(UserService userservice){this.userservice = userservice;}
}
