package com.idiot.blog.entity;
/**
 * @auther idiot
 * @date 2019/6/30 - 20:54
 **/

import com.idiot.blog.common.CommonEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *@ClassName:comment
 *@Description:TODO
 *@Version:1.0
 **/
@Entity
public class Comment extends CommonEntity {
    private String imageUrl;    //头像地址(预留==)
    private Article article;    //关联文章
    private String nickName="匿名";    //昵称
    private String email;       //邮箱
    private String userUrl;     //地址
    private String content;     //内容
    private String time;        //时间

    @ManyToOne
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
