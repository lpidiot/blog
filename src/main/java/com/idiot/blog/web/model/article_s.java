package com.idiot.blog.web.model;

/**归档中的文章
 * @ClassName:article_s
 * @Description:TODO
 * @Version:1.0
 **/
public class article_s {
    private Integer id;     //文章索引暂时还是用id吧。。
    private String title;   //标题
    private String time;    //时间

    public article_s(Integer id, String title, String time) {
        this.id = id;
        this.title = title;
        this.time = time;
    }

    public article_s(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
