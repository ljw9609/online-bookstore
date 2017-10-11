package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lvjiawei on 2017/4/12.
 */
@Entity
public class UsersEntity {
    private int id;
    private String username;
    private String password;
    private String confirmpassword;
    private String email;
    private int is_admin;
    private String profile_id;

    private String image_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIs_admin(){return is_admin;}

    public void setIs_admin(int is_admin){this.is_admin = is_admin;}

    public String getProfile_id(){return profile_id;}

    public void setProfile_id(String profile_id){this.profile_id = profile_id;}

    public String getImage_id(){return image_id;}

    public void setImage_id(String image_id){this.image_id = image_id;}


    public UsersEntity(){}

    private Set participants = new HashSet();
    public Set getParticipants() { return participants; }
    public void setParticipants(Set participants) {
        this.participants = participants;
    }
}

