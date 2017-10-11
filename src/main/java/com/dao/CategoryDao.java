package com.dao;

import java.util.List;

/**
 * Created by lvjiawei on 2017/6/25.
 */
public interface CategoryDao extends BaseDao{
    List getCategory();
    int getIdByName(String name);

}
