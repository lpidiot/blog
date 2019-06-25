package com.idiot.blog.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:TagModel
 * @Description:TODO
 * @Version:1.0
 **/
public class TagModel {
    private String tag;
    private List<article_s> articles=new ArrayList<>();


    public TagModel(String tag, List<article_s> articles) {
        this.tag = tag;
        this.articles = articles;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<article_s> getArticles() {
        return articles;
    }

    public void setArticles(List<article_s> articles) {
        this.articles = articles;
    }
}
