package com.idiot.blog.web.controller;

import com.idiot.blog.entity.Article;
import com.idiot.blog.entity.Comment;
import com.idiot.blog.service.ArticleService;
import com.idiot.blog.service.CommentService;
import com.idiot.blog.utils.PageInfo;
import com.idiot.blog.utils.TagUtilv2;
import com.idiot.blog.web.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName:MainController
 * @Description:TODO
 * @Version:1.0
 **/
@Controller
public class BlogController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

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
        //System.err.println(article_page.getContent().get(0).getComments());
        PageInfo pageInfo = new PageInfo(article_page.getTotalElements(), article_page.getNumber() + 1, article_page.getTotalPages(), article_page.getNumberOfElements());
        map.put("article_list", article_page.getContent());
        map.put("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/getArticle")
    @ResponseBody
    public List<Article> getArticle(PageModel page, ModelMap map) {
        if (page.getPage() != 0) {
            page.setPage(page.getPage() - 1);
        }
        List<Sort.Order> sort = new ArrayList<>();
        sort.add(new Sort.Order(Sort.Direction.DESC, "time"));
        sort.add(new Sort.Order(Sort.Direction.DESC, "id"));
        PageRequest pageRequest = PageRequest.of(page.getPage(), page.getSize(), new Sort(sort));
        Page<Article> article_page = articleService.findAll(pageRequest);
        //System.err.println(article_page.getContent().get(0).getComments());
        return article_page.getContent();
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

    @RequestMapping(value = "/addComment")
    @ResponseBody
    public Object addComment(Comment model, Integer articleId) {
        System.out.println(model.getContent());
        if(model.getNickName()==null||model.getNickName()==""){
            model.setNickName("匿名");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        model.setTime(format.format(new Date()));
        model.setArticle(articleService.findById(articleId));
        commentService.save(model);
        return "ok";
    }

    @RequestMapping(value = "/getComment")
    @ResponseBody
    public Object getComment(PageModel page, Integer articleId) {
        if (page.getPage() != 0) {
            page.setPage(page.getPage() - 1);
        }
        PageRequest pageRequest = PageRequest.of(page.getPage(), page.getSize(),new Sort(Sort.Direction.DESC,"id"));
        //  List<Comment> comments = commentService.findByArticle(articleService.findById(articleId));

        Specification<Comment> spec = new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("article").get("id"), articleId);
            }
        };
        Page<Comment> comments_page = commentService.findAll(spec, pageRequest);

        PageInfo pageInfo = new PageInfo(comments_page.getTotalElements(), comments_page.getNumber() + 1, comments_page.getTotalPages(), comments_page.getNumberOfElements());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pageInfo",pageInfo);
        hashMap.put("comments",comments_page.getContent());
        return hashMap;
    }


    @RequestMapping(value = "/detail2")
    public void detail2(Integer id, ModelMap map) {
        map.put("article", articleService.findById(id));
        map.put("prevArticle", articleService.getPrev(id));
        map.put("nextArticle", articleService.getNext(id));
    }

}
