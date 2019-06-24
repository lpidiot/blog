package com.idiot.blog.web.controller;

import com.idiot.blog.entity.Article;
import com.idiot.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public void detail(Integer id,ModelMap map) {
        Article lessThan = articleService.findByIdLessThan(id);
        Article greaterThan = articleService.findByIdGreaterThan(id);
        map.put("article",articleService.findById(id));
        map.put("lessThan",lessThan);
        map.put("greaterThan",greaterThan);
        if(lessThan!=null){
            System.out.println("lessThan");
            System.out.println(lessThan.getTitle());
        }
        if(greaterThan!=null){
            System.out.println("greaterThan");
            System.out.println(greaterThan.getTitle());
        }

    }

    @RequestMapping(value = "/index")
    public void index(ModelMap map) {
        map.put("article_list",articleService.findAll());
    }
}
