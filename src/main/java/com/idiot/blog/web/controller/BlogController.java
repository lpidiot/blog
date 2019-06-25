package com.idiot.blog.web.controller;

import com.idiot.blog.entity.Article;
import com.idiot.blog.service.ArticleService;
import com.idiot.blog.web.model.archivesModel;
import com.idiot.blog.web.model.article_s;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.idiot.blog.utils.tagUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:MainController
 * @Description:TODO
 * @Version:1.0
 **/
@Controller
public class BlogController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/detail")
    public void detail(Integer id, ModelMap map) {
        List<Article> lessThan = articleService.findByIdLessThan(id);
        List<Article> greaterThan = articleService.findByIdGreaterThan(id);
        map.put("article", articleService.findById(id));
        if (lessThan.size() > 0) {
            map.put("lessThan", lessThan.get(0));
        }
        if (greaterThan.size() > 0) {
            map.put("greaterThan", greaterThan.get(0));
        }

    }

    @RequestMapping(value = "/index")
    public void index(ModelMap map) {
        map.put("article_list", articleService.findAll());
    }


    @RequestMapping(value = "/tag")
    public void tag(ModelMap map) {
        tagUtil tagUtil = new tagUtil(articleService.findAll());
        map.put("tag_article_list", tagUtil.getTagModel_list());
    }

    @RequestMapping(value = "/archives")
    public void archives(ModelMap map) {
        List<Article> article_list = articleService.findAllByOrderByTimeDesc();
        map.put("archives", articleService.createArchives(article_list));
    }
}
