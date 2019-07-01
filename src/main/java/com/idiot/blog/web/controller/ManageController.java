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

    @RequestMapping(value = "/recode")
    public void recode(Integer id, ModelMap map) {
        if (id != null) {
            map.put("article", articleService.findById(id));
        }
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(ArticleModel model) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            Article article;
            String content = model.getContent();
            if (content.length() > 147) {
                model.setSketch(content.substring(0, 147) + "...");
            } else {
                model.setSketch(content + "...");
            }


            if (model.getId() == null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                model.setTime(dateFormat.format(new Date()));
                article = new Article();
                BeanUtils.copyProperties(model, article);
            } else {
                article = articleService.findById(model.getId());
                BeanUtils.copyProperties(model, article, "time");
            }
            articleService.save(article);
            hashMap.put("state", true);
            hashMap.put("content", "保存成功");
        } catch (Exception e) {
            System.out.println(e);
            hashMap.put("state", false);
            hashMap.put("content", "保存失败");
        }
        return hashMap;
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

    @RequestMapping(value = "/articleManage")
    public void articleManage(String flag,ModelMap map) {
        if(flag!=null){
            map.put("message","操作成功");
        }
        map.put("article_list", articleService.findAllByOrderByTimeDesc());
    }

    @RequestMapping(value = "/page")
    @ResponseBody
    public Object page() {
        return articleService.findAllByOrderByTimeDesc();
    }


}
