package com.idiot.blog.web.controller;

import com.idiot.blog.entity.Article;
import com.idiot.blog.service.ArticleService;
import com.idiot.blog.utils.PageInfo;
import com.idiot.blog.utils.TagUtilv2;
import com.idiot.blog.web.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        map.put("article", articleService.findById(id));
        map.put("prevArticle", articleService.getPrev(id));
        map.put("nextArticle", articleService.getNext(id));
    }

    @RequestMapping(value = "/index")
    public void index(PageModel page, ModelMap map) {
        if (page.getPage() != 0) {
            page.setPage(page.getPage() - 1);
        }
        List<Sort.Order> sort = new ArrayList<>();
        sort.add(new Sort.Order(Sort.Direction.DESC, "time"));
        sort.add(new Sort.Order(Sort.Direction.DESC, "id"));
        PageRequest pageRequest = PageRequest.of(page.getPage(), page.getSize(), new Sort(sort));
        Page<Article> article_page = articleService.findAll(pageRequest);
        PageInfo pageInfo = new PageInfo(article_page.getTotalElements(), article_page.getNumber() + 1, article_page.getTotalPages(), article_page.getNumberOfElements());
        map.put("article_list", article_page.getContent());
        map.put("pageInfo", pageInfo);
    }


    @RequestMapping(value = "/tag")
    public void tag(ModelMap map) {
        //tagUtil tagUtil = new tagUtil(articleService.findAllByOrderByTimeDesc());
        TagUtilv2 tagUtil = new TagUtilv2();

        map.put("tag_article_list", tagUtil.createTagArticle(articleService.findTag(), articleService.findAllByOrderByTimeDesc()));
        map.put("tags", tagUtil.getTags(articleService.findTag()));
    }

    @RequestMapping(value = "/archives")
    public void archives(ModelMap map) {
        List<Article> article_list = articleService.findAllByOrderByTimeDesc();
        map.put("archives", articleService.createArchives(article_list));
    }
}
