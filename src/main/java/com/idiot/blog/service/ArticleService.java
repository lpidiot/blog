package com.idiot.blog.service;

import com.idiot.blog.common.CommonService;
import com.idiot.blog.entity.Article;
import com.idiot.blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:ArticleService
 * @Description:TODO
 * @Version:1.0
 **/
@Service
public class ArticleService extends CommonService<Article,Integer> {
    @Autowired
    private ArticleRepository repository;

    public Article findByIdLessThan(Integer id){
        return repository.findByIdLessThan(id);
    }
}
