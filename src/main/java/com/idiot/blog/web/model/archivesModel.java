package com.idiot.blog.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 归档模型
 * @ClassName:archivesModel
 * @Description:TODO
 * @Version:1.0
 **/
public class archivesModel {
    private String day; //日期
    private List<article_s> articles=new ArrayList<>(); //日期对应的文章


    public archivesModel(String day, List<article_s> articles) {
        this.day = day;
        this.articles = articles;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<article_s> getArticles() {
        return articles;
    }

    public void setArticles(List<article_s> articles) {
        this.articles = articles;
    }
}
