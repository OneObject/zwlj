package com.zwlj.common.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.zwlj.common.query.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QueryUtil {

    public static int getFirstResult(Page page) {
        if(page != null) {
            return (page.getCurrent_page() - 1) * page.getPage_size();
        }
        return 0;
    }

    public static int getMaxResult(Page page) {
        if (page != null) {
            return page.getPage_size();
        }
        return 0;
    }

    public static Map<String,Object> generateParamMap(QueryCondition search){
        List<QueryFilter> filters = search.getFilters();
        if (null != filters && !filters.isEmpty()) {
            QueryFilter allFilter = QueryFilter.Joiner.and(filters);
            return QueryFilterParser.parse(allFilter).getConditionParamValueMap();
        }
        return Maps.newHashMap();
    }

    public static String generateSQL(String selectFields, String tableName, QueryCondition search) {

        if (null == search) {
            search = QueryCondition.EMPTY;
        }
        String sql_select = selectFields;
        if (StringUtils.isBlank(sql_select)) {
            List<String> fields = search.getFields();
            if (null != fields && !fields.isEmpty()) {
                sql_select = Joiner.on(",").skipNulls().join(fields);
            } else {
                sql_select = "*";
            }
        }
        if (search.isDistinct()) {
            sql_select = String.format("distinct(%s)", sql_select);
        }

        String sql_from = tableName;

        Validate.notBlank(sql_from, "table name must not empty");

        String sql_where = StringUtils.EMPTY;
        List<QueryFilter> filters = search.getFilters();
        if (null != filters && !filters.isEmpty()) {
            QueryFilter allFilter = QueryFilter.Joiner.and(filters);
            sql_where = String.format("where %s",QueryFilterParser.parse(allFilter).getConditionParamXql());
        }

        String sql_order = StringUtils.EMPTY;

        List<Sort> sorts = search.getSorts();
        if (null != sorts && !sorts.isEmpty()) {

            StringBuilder sb = new StringBuilder("order by ");
            boolean isFirst = true;
            for (Sort sort : sorts) {
                sb.append(String.format("%s %s %s", isFirst ? "" : ",", sort.getProperty(), sort.isDesc() ? "desc" : "asc"));
                isFirst = false;
            }
            sql_order = sb.toString();
        }

        //return String.format("select %s from %s %s %s", sql_select,sql_from,sql_where,sql_order);
        return String.format(" from %s %s %s", sql_from,sql_where,sql_order);
    }

    public static String generateCountSQL(String tableName, QueryCondition search) {
        if (null == search) {
            search = QueryCondition.EMPTY;
        }

        String sql_from = tableName;

        Validate.notBlank(sql_from, "table name must empty");

        String sql_where = StringUtils.EMPTY;
        List<QueryFilter> filters = search.getFilters();
        if (null != filters && !filters.isEmpty()) {
            QueryFilter allFilter = QueryFilter.Joiner.and(filters);
            sql_where = String.format("where %s",QueryFilterParser.parse(allFilter).getConditionParamXql());
        }

        return String.format("select count(*) from %s %s ",sql_from,sql_where);
    }



    public static void main(String[] args) {

        String selectFields="id,name,age";
        String tableName="user u , org o ";
        QueryCondition search=new QueryCondition();
        search.setDistinct(true);
        search.addSort(Sort.asc("name"));
        search.addSort(Sort.desc("id"));

        search.addFilter(QueryFilter.equal("name","songsp"));
        search.addFilter(QueryFilter.greaterOrEqual("age",10));
        search.addFilter(QueryFilter.Joiner.or(QueryFilter.notEqual("name", "zhang"),QueryFilter.like("nick", "song")));
        search.addFilter(QueryFilter.custom("a.id = b.id"));

        String sql = QueryUtil.generateSQL(selectFields, tableName, search);

        Map<String,Object> paramMap = QueryUtil.generateParamMap(search);
        System.out.println(sql);
        System.out.println(paramMap);
    }


}

