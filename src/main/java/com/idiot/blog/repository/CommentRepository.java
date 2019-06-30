package com.idiot.blog.repository;
/**
 * @auther idiot
 * @date 2019/6/30 - 21:30
 **/

import com.idiot.blog.common.CommonRepository;
import com.idiot.blog.entity.Article;
import com.idiot.blog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@InterfaceName:CommentRepository
 *@Description:TODO
 *@Version:1.0
 **/
@Repository
public interface CommentRepository extends CommonRepository<Comment,Integer> {
    public List<Comment> findByArticle(Article article);
}
