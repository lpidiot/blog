package com.idiot.blog.repository;

import com.idiot.blog.common.CommonRepository;
import com.idiot.blog.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName:ArticleRepository
 * @Description:TODO
 * @Version:1.0
 **/
@Repository
public interface ArticleRepository extends CommonRepository<Article,Integer> {
    public List<Article> findByIdLessThan(Integer id);
    public List<Article> findByIdGreaterThan(Integer id);
    public List<Article> findAllByOrderByTimeDescIdDesc();

    @Query(value ="SELECT tag FROM article ORDER BY time DESC, id DESC",nativeQuery = true)
    public List<String> findTag();
}
