package com.idiot.blog.service;

import com.idiot.blog.common.CommonService;
import com.idiot.blog.entity.Article;
import com.idiot.blog.repository.ArticleRepository;
import com.idiot.blog.web.model.TagModel;
import com.idiot.blog.web.model.archivesModel;
import com.idiot.blog.web.model.article_s;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:ArticleService
 * @Description:TODO
 * @Version:1.0
 **/
@Service
public class ArticleService extends CommonService<Article, Integer> {
    @Autowired
    private ArticleRepository repository;

    public List<Article> findByIdLessThan(Integer id) {
        return repository.findByIdLessThan(id);
    }

    public List<Article> findByIdGreaterThan(Integer id) {
        return repository.findByIdGreaterThan(id);
    }

    public List<Article> findAllByOrderByTimeDesc() {
        return repository.findAllByOrderByTimeDescIdDesc();
    }

    public List<String> findTag(){
     return repository.findTag();
    }

    public List<archivesModel> createArchives(List<Article> article_list) {
        List<archivesModel> archivesModel_list = new ArrayList<>(); //归档列表
        List<List<article_s>> article_s_lists = new ArrayList<>(); //时间段文章列表
        for (int i = 0; i < article_list.size(); i++) {
            article_s_lists.add(new ArrayList<article_s>());
        }
        Integer idx = 0;
        String flag = null;     //用于区分各个时间段(day)和下面的day区分开

        for (int i = 0; i < article_list.size(); i++) {      //这里直接遍历的话无法处理==
            Article article = article_list.get(i);      //取出对象备用
            String day = article.getTime().substring(0, 7);    //获得年月(2019-06)
            if (flag == null) {     //将同时间文章添加到同一个数组
                flag = day;         //flag 年月时间
            }
            if (day.equals(flag)) {     //如果对象的年月时间和flag相同，那么该文章在flag时间内
                article_s_lists.get(idx).add(new article_s(article.getId(), article.getTitle(), article.getTime().substring(5, 10)));
                continue;   //添加时间段文章后直接开始下一次循环
            }
            //如果对象的年月时间和flag不同，那么上一个时间段的所有文章已经添加到了时间段文章里，保存下该归档
            archivesModel_list.add(new archivesModel(flag, article_s_lists.get(idx)));
            idx++;
            flag = null;    //置空flag，开始下一个时间段
            i = i - 1;      //这里是不满足才保存归档的，会跳一次循环，不然每有一个对象时间段时间和flag不同就会少循环一次==
        }
        //最后一次循环结束后没有调用保存归档，补上就行了，此时flag肯定不为空
        if (flag != null) {
            archivesModel_list.add(new archivesModel(flag, article_s_lists.get(idx)));
        }
        return archivesModel_list;
    }
}
