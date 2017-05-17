package com.zwlj.common.query;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class QueryCondition {

    protected List<QueryFilter> filters = Collections.EMPTY_LIST;
    protected List<Sort> sorts = Collections.EMPTY_LIST;
    protected List<String> fields = Collections.EMPTY_LIST;
    protected boolean distinct;

    public static final QueryCondition EMPTY = new QueryCondition();

    public  QueryCondition() {}

    public static QueryCondition  newInstance(){
        return new QueryCondition();
    }

    public QueryCondition addEqualFilter(String property,Object value){
        return addFilter(QueryFilter.equal(property, value));
    }

    public QueryCondition addFilter(QueryFilter filter){
        if(filters.isEmpty()){
            filters = Lists.newArrayList();
        }
        filters.add(filter);
        return this;
    }

    public QueryCondition addSort(Sort sort){
        if(sorts.isEmpty()){
            sorts = Lists.newArrayList();
        }
        sorts.add(sort);
        return this;
    }

    public QueryCondition addField(String field){
        if(fields.isEmpty()){
            fields = Lists.newArrayList();
        }
        fields.add(field);
        return this;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public List<QueryFilter> getFilters() {
        return Collections.unmodifiableList(filters);
    }

    public List<Sort> getSorts() {
        return Collections.unmodifiableList(sorts);
    }

    public List<String> getFields() {
        return Collections.unmodifiableList(fields);
    }

}
