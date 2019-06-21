package com.idiot.blog.repository;

import com.idiot.blog.common.CommonRepository;
import com.idiot.blog.entity.Article;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName:ArticleRepository
 * @Description:TODO
 * @Version:1.0
 **/
@Repository
public interface ArticleRepository extends CommonRepository<Article,Integer> {
    public Article findByIdLessThan(Integer id);
    public Article findByIdGreaterThan(Integer id);
}
