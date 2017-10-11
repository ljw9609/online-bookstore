package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lvjiawei on 2017/4/25.
 */
public class BooksEntity {
    private int id;
    private String isbn;
    private String title;
    private String author;
    private String press;
    private String intro;
    private Double price;
    private int amount;
    private int categoryid;

    private String profile_id;

    private String image_id;

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getIsbn(){return isbn;}

    public void setIsbn(String isbn){this.isbn = isbn;}

    public String getTitle(){return title;}

    public void setTitle(String title){this.title = title;}

    public String getAuthor(){return author;}

    public void setAuthor(String author){this.author = author;}

    public String getPress(){return press;}

    public void setPress(String press){this.press = press;}

    public String getIntro(){return intro;}

    public void setIntro(String intro){this.intro = intro;}

    public Double getPrice(){return price;}

    public void setPrice(Double price){this.price = price;}

    public int getAmount(){return amount;}

    public void setAmount(int amount){this.amount = amount;}

    public int getCategoryid(){return categoryid;}

    public void setCategoryid(int categoryid){this.categoryid = categoryid;}

    public String getProfile_id(){return profile_id;}

    public void setProfile_id(String profile_id){this.profile_id = profile_id;}

    public String getImage_id(){return image_id;}

    public void setImage_id(String image_id){this.image_id = image_id;}

    public BooksEntity(){}

    private Set participants = new HashSet();
    public Set getParticipants() { return participants; }
    public void setParticipants(Set participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooksEntity book = (BooksEntity) o;

        return id == book.id;
    }


    @Override
    public int hashCode() {
        System.out.println("hash "+id+"");
        return id;
    }
}
