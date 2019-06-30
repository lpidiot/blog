package com.idiot.blog.web.model;
/**
 * @auther idiot
 * @date 2019/6/30 - 22:29
 **/

import com.idiot.blog.entity.Comment;
import com.idiot.blog.utils.PageInfo;

import java.util.List;

/**
 *@ClassName:Comment_pageInfo
 *@Description:TODO
 *@Version:1.0
 **/
public class Comment_pageInfo {
    private PageInfo pageInfo;
    private List<Comment> comments;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
