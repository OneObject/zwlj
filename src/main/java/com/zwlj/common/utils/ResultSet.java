package com.zwlj.common.utils;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

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
        this.items = items;
        this.page = new Page(current_page, page_size, total_size);
    }

    public ResultSet(SearchResult<T> searchResult, ISearch iSearch) {
        if (null == searchResult) return;
        this.items = searchResult.getResult();
        this.page = new Page();
        this.page.setCurrent_page(iSearch.getPage() + 1);
        this.page.setPage_size(iSearch.getMaxResults());
        this.page.setTotal_size(searchResult.getTotalCount());
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
