package com.idiot.blog.web.model;

/**
 * 分页参数
 * @ClassName:PageModel
 * @Description:TODO
 * @Version:1.0
 **/
public class PageModel {
    private int page=0; //页数
    private int size=5;    //每页数量
    private String order;   //排序字段
    private String sort;    //排序方式

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
