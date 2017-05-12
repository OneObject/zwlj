package com.zwlj.common.utils;

import com.google.common.collect.Lists;

import java.util.List;

public class QueryCondition {

	List<QueryFilter> filters = Lists.newArrayList();

	Sort sort ;

	public QueryCondition() {

	}

	public QueryCondition addFilter(QueryFilter filter) {
		filters.add(filter);
		return this;
	}
	
	/*
	 * 默认为 equal 条件
	 * @param feild
	 * @param value
	 * @return
	 */
	public QueryCondition addFilter(String feild, Object value){
		addFilterEqual(feild, value);
		return this;
	}
	
	public QueryCondition addFilterEqual(String feild, Object value){
		filters.add(QueryFilter.equal(feild, value));
		return this;
	}
	public QueryCondition addFilterNotEqual(String feild, Object value){
		filters.add(QueryFilter.notEqual(feild, value));
		return this;
	}
	
	public QueryCondition addFilterLike(String feild, String value){
		filters.add(QueryFilter.like(feild, value));
		return this;
	}
	
	public QueryCondition addFilter(String feild, Object value, int operator){
		filters.add(new QueryFilter(feild, value, operator));
		return this;
	}
	
	
	
	
	public QueryCondition  addSort(Sort sort){
		this.sort = sort;
		return this;
	}
	
	public QueryCondition addOrder(String feild, Sort.Order order){
		if(this.sort==null){
			sort = new Sort();
		}
		this.sort.on(feild, order);
		return this;
	}



	public List<QueryFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<QueryFilter> filters) {
		this.filters = filters;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
	
	
}
