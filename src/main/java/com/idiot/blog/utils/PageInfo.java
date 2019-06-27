package com.idiot.blog.utils;

/**
 * @ClassName:PageUtil
 * @Description:TODO
 * @Version:1.0
 **/
public class PageInfo {
     private long totalElements;    //总记录数
     private int currentPage;            //当前页
     private int totalPages;        //总页数
     private int numberOfElements;  //当前页面的记录数

    public PageInfo(long totalElements, int currentPage, int totalPages, int numberOfElements) {
        this.totalElements = totalElements;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
