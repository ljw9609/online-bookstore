package com.entity;

import java.util.Set;

/**
 * Created by lvjiawei on 2017/6/8.
 */
public class CategoryEntity {
    private int categoryID;
    private String name;

    public CategoryEntity() {
        super();
    }

    public CategoryEntity(int categoryID, String name, Set<BooksEntity> books) {
        super();
        this.categoryID = categoryID;
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
