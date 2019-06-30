package com.idiot.blog.service;
/**
 * @auther idiot
 * @date 2019/6/30 - 21:31
 **/

import com.idiot.blog.common.CommonService;
import com.idiot.blog.entity.Article;
import com.idiot.blog.entity.Comment;
import com.idiot.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@ClassName:CommentService
 *@Description:TODO
 *@Version:1.0
 **/
@Service
public class CommentService extends CommonService<Comment,Integer> {
    @Autowired
    private CommentRepository repository;

    public List<Comment> findByArticle(Article article){
        return repository.findByArticle(article);
    }
}
