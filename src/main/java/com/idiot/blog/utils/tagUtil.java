package com.idiot.blog.utils;

import com.idiot.blog.entity.Article;
import com.idiot.blog.web.model.TagModel;
import com.idiot.blog.web.model.article_s;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将文章转化为按标签分类的格式
 * @ClassName:tagUtil
 * @Description:TODO
 * @Version:1.0
 **/
public class tagUtil {
    private List<Article> article_list; //文章列表
    private List<TagModel> tagModel_list = new ArrayList<>();
    private List<List<article_s>> article_s_lists = new ArrayList<>(); //标签-文章列表
    private List<String> tags = null;  //标签列表
    private int index = 0;             //当前操作文章索引(article_list)主要用作取出tag
    private int article_s_index = 0;   //标签-文章列表索引(article_s_lists)
    private int tag_index = 0;         //当前标签索引(tags)


    public tagUtil(List<Article> article_list) {
        this.article_list = article_list;
        //准备数据
        init();
    }

    /**
     * 初始数据，准备存文章的容器
     */
    private void init() {
        //准备容器
        for (int i = 0; i < article_list.size(); i++) {
            article_s_lists.add(new ArrayList<article_s>());
        }
        //准备tag数据
        Article article = article_list.get(0);      //取出对象
        String[] flag = null;
        if (article.getTag().indexOf(",") == -1) {      //单标签
            flag = new String[]{article.getTag()};
        } else {
            flag = article.getTag().split(",");
        }
        tags = new ArrayList<>(Arrays.asList(flag));

    }

    /**
     * 返回分好的标签文章
     * @return
     */
    public List<TagModel> getTagModel_list() {
        //创建tag
        createTag();
        return tagModel_list;
    }

    /**
     * 下一个要操作的标签列表(从下一篇操作文章中取==)
     */
    private void nextTag() {
        //开始下一篇文章
        index++;
        //准备tag数据
        try {
            Article article = article_list.get(index);      //取出对象
            String[] flag = null;
            if (article.getTag().indexOf(",") == -1) {      //单标签
                flag = new String[]{article.getTag()};
            } else {
                flag = article.getTag().split(",");
            }
            if (tags == null) {
                tags = new ArrayList<>(Arrays.asList(flag));
            } else {
                int idx = 0;  //标记添加新标签的数量
                for (String s : flag) {
                    if (!tags.contains(s)) {
                        tags.add(s);
                        idx++;
                    }
                }
                if (idx == 0) {
                    //这个标签已经被处理过，换下一个
                    nextTag();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return;        //已解析到文章末尾，没有需操作的标签了
        }


    }

    public String getCurrentTag() {
        try {
            return tags.get(tag_index);
        } catch (Exception e) {
            return null;        //超出list的范围，要开始准备下一次的标签了
        }
    }

    public void createTag() {
        String currentTag = getCurrentTag();
        if (currentTag == null) {
            //当前操作标签列表到头了/还没有标签，添加下一个标签/添加标签
            nextTag();
            if (getCurrentTag() == null) {
                //再次为空，已全部操作完成
                return;
            } else {
                currentTag = getCurrentTag();
            }
        }
        for (Article article : article_list) {
            List<String> currentTags = null;                    //当前文章标签
            if (article.getTag().indexOf(",") == -1) {
                currentTags = Arrays.asList(new String[]{article.getTag()});
            } else {
                currentTags = Arrays.asList(article.getTag().split(","));
            }
            if (currentTags.contains(currentTag)) {
                article_s_lists.get(article_s_index).add(new article_s(article.getId(), article.getTitle()));
            }
        }
        //循环完毕后当前操作标签的文章已准备完成，保存
        tagModel_list.add(new TagModel(getCurrentTag(), article_s_lists.get(article_s_index)));
        //开始下一个标签
        article_s_index++;
        tag_index++;
        //  System.out.println(tag_index);
        createTag();
    }
}
