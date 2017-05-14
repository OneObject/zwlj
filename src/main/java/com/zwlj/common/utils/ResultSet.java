package com.zwlj.common.utils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ResultSet<T> implements Serializable {

    /**
     * 数据记录
     */
    private List<T> items;
    private Page page;

    public ResultSet(){}

    public ResultSet(List<T> items,int page_size,int current_page, int total_size) {
        this.setItems(items);
        this.page = new Page(current_page, page_size, total_size);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        if(null == items) {
            this.items = Collections.emptyList();
        }
        this.items = items;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ResultSet{" +
                "items=" + items +
                ", page=" + page +
                '}';
    }
}
