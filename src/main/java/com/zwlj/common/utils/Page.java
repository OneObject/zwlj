package com.zwlj.common.utils;

import java.io.Serializable;

public class Page implements Serializable {

    public final static int PAGE_SIZE_DEFAULT = 10;
    public final static int CURRENT_PAGE_DEFAULT = 1;

    private int page_size = PAGE_SIZE_DEFAULT;  // 每页条数，<=0 代表不分页
    private int current_page = CURRENT_PAGE_DEFAULT;    //当前页
    private long total_size; //总条数

    public Page() {}

    public Page(int page_size) {
        this.page_size = page_size;
    }

    public Page(int current_page, int page_size) {
        this.setCurrent_page(current_page);
        this.setPage_size(page_size);
    }

    public Page(int current_page, int page_size, long total_size) {
        this.setCurrent_page(current_page);
        this.setPage_size(page_size);
        this.setTotal_size(total_size);
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        if (page_size < 1) {
            this.page_size = Integer.MAX_VALUE;
        }
        this.page_size = page_size;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        if(current_page < 1) {
            this.current_page = CURRENT_PAGE_DEFAULT;
        }
        this.current_page = current_page;
    }

    public long getTotal_size() {
        return total_size;
    }

    public void setTotal_size(long total_size) {
        this.total_size = total_size;
    }

    // 总共多少页
    public long getTotal_page() {
        if (page_size <= 0)
            return 1;
        return (this.total_size + this.page_size - 1) / this.page_size;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page_size=" + page_size +
                ", current_page=" + current_page +
                ", total_size=" + total_size +
                '}';
    }
}
