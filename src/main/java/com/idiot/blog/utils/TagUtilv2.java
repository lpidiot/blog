package com.idiot.blog.utils;

import com.idiot.blog.entity.Article;
import com.idiot.blog.web.model.TagModel;
import com.idiot.blog.web.model.article_s;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将文章转化为按标签分类的格式
 * @ClassName:TagUtilv2
 * @Description:TODO
 * @Version:1.0
 **/
public class TagUtilv2 {
    private List<String> tags=new ArrayList<>();    //标签列表
    List<TagModel> TagModels=new ArrayList<>();     //目标列表
    private List<List<article_s>> article_s_lists=new ArrayList<>(); //标签-文章列表
    private int article_s_index = 0;   //标签-文章列表索引(article_s_lists)

    public List<String> getTags(List<String> tags_origin){
        if(tags.size()==0){
            for(String tag:tags_origin){
                if(tag.indexOf(",")==-1){
                    tags.add(tag);
                }else{
                    String[] flag=tag.split(",");
                    for(String s:flag){
                        tags.add(s);
                    }
                }
            }
        }
        return tags;
    }

    public List<TagModel> createTagArticle(List<String> tag_list,List<Article> article_list){
        List<String> tag_flag=null;
        if(tags.size()==0){
            tag_flag = getTags(tag_list);
        }else {
            tag_flag=tags;
        }
        //准备容器
        for (int i = 0; i < tag_flag.size(); i++) {
            article_s_lists.add(new ArrayList<article_s>());
        }
        for(String tag:tag_flag){
            for(Article article:article_list){
                String[] flag=null;
                if (article.getTag().indexOf(",") == -1) {      //单标签
                    flag = new String[]{article.getTag()};
                } else {
                    flag = article.getTag().split(",");
                }
                List<String> flag_array=Arrays.asList(flag);
                if(flag_array.contains(tag)){
                    article_s_lists.get(article_s_index).add(new article_s(article.getId(), article.getTitle()));
                }
                continue;
            }
            TagModels.add(new TagModel(tag, article_s_lists.get(article_s_index)));
            article_s_index++;
        }
        return TagModels;
    }

}
