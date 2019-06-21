package com.idiot.blog.web.controller;

import com.idiot.blog.entity.Article;
import com.idiot.blog.service.ArticleService;
import com.idiot.blog.web.model.ArticleModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


/**
 * @ClassName:ManagerController
 * @Description:TODO
 * @Version:1.0
 **/
@Controller
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/index")
    public void index(Integer id,ModelMap map) {
        if(id!=null){
            map.put("article",articleService.findById(id));
        }
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public void save(ArticleModel model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.setTime(dateFormat.format(new Date()));
        String content = model.getContent();
        if (content.length() > 147) {
            model.setSketch(content.substring(0, 147) + "...");
        } else {
            model.setSketch(content + "...");
        }
        Article article = new Article();
        BeanUtils.copyProperties(model, article);
        articleService.save(article);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Integer id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            articleService.deleteById(id);
            hashMap.put("state", true);
            hashMap.put("content", "删除文章完成");
        } catch (Exception e) {
            hashMap.put("state", false);
            hashMap.put("content", "操作失败(500)");
            return hashMap;
        }
        return hashMap;
    }

    @RequestMapping(value = "/article")
    public void article(ModelMap map) {
        map.put("article_list", articleService.findAll());
    }

    @RequestMapping(value = "/page")
    @ResponseBody
    public Object page() {
        return articleService.findAll();
    }


}
