package com.idiot.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idiot.blog.common.CommonEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:Article
 * @Description:文章
 * @Version:1.0
 **/
@Entity
public class Article extends CommonEntity {
    private String type;             //文章格式(备用)
    private String title;            //文章标题
    private String time;             //时间
    private String sketch;           //简要
    private String content;          //文章内容
    private String category;         //分类
    private String tag;              //标签
    private Integer access = 0;      //访问数
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();         //评论

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
//    @Column(columnDefinition="DATE")


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Column(length = 200)
    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    @Column(length = 16777216)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    @Transient
    public List<String> getFlagTag() {
        List<String> list = new ArrayList<>();
        for (String s : tag.split(",")) {
            list.add(s);
        }
        return list;
    }

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
