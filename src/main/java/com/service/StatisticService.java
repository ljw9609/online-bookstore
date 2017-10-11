package com.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lvjiawei on 2017/6/24.
 */
public interface StatisticService {
    String show_second(HttpServletResponse response,String method) throws IOException;
    String show_bookinfo(HttpServletResponse response,String title) throws IOException;
    Double statistic_by(String param);
    String statistic(HttpServletResponse response,String param) throws IOException;
    List<String> getUserList();
    List<String> getYearList();
    List<String> getCategoryList();
    List<String> getBooknameList();
}
