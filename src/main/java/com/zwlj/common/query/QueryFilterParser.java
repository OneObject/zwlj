package com.zwlj.common.query;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class QueryFilterParser {

    private String sql;

    private Map<String,Object> paramMap = Maps.newHashMap();

    private volatile AtomicInteger paramIndex = new AtomicInteger();

    private QueryFilter filter;

    private QueryFilterParser(QueryFilter filter){
        this.filter = filter;
    }

    public static QueryFilterParser parse(QueryFilter filter){
        QueryFilterParser parser  =  new QueryFilterParser(filter);
        parser.build();
        return parser;
    }

    public static QueryFilterParser parse(List<QueryFilter> filters){
        QueryFilter allFilter = QueryFilter.Joiner.and(filters);
        return parse(allFilter);
    }

    public String getConditionParamXql() {
        return sql;
    }

    public Map<String, Object> getConditionParamValueMap() {
        return paramMap;
    }

    @Override
    public String toString() {
        return String.format("value=[ sql=%s , paramMap=%s ]", sql, paramMap);
    }

    private void build(){
        sql = build(this.filter,paramMap,paramIndex);
    }

    private String build(QueryFilter subFilter,Map<String,Object> parentParamMap ,AtomicInteger paramIndex ){
        String property = subFilter.getProperty();
        String paramArg = subFilter.getProperty();
        Object value = subFilter.getValue();
        int operator = subFilter.getOperator();
        if(subFilter.isTakesValue()){
            paramArg = String.format("%s_%s", paramArg,paramIndex.getAndIncrement());
            parentParamMap.put(paramArg, subFilter.getValue());
        }
        switch (operator) {
            case QueryFilter.OP_IN:
                return property + " in (:"+paramArg+")";
            case QueryFilter.OP_NOT_IN:
                return property + " not in (:"+paramArg+")";
            case QueryFilter.OP_EQUAL:
                return property + " = " + ":"+paramArg;
            case QueryFilter.OP_NOT_EQUAL:
                return property + " != " + ":"+paramArg;
            case QueryFilter.OP_GREATER_THAN:
                return property + " > " + ":"+paramArg ;
            case QueryFilter.OP_LESS_THAN:
                return property + " < " + ":"+paramArg ;
            case QueryFilter.OP_GREATER_OR_EQUAL:
                return property + " >= " + ":"+paramArg ;
            case QueryFilter.OP_LESS_OR_EQUAL:
                return property + " <= " + ":"+paramArg;
            case QueryFilter.OP_LIKE:
                return property + " LIKE "  + ":"+paramArg;
            case QueryFilter.OP_ILIKE:
                return property + " ILIKE " + ":"+paramArg;
            case QueryFilter.OP_NULL:
                return property + " IS NULL";
            case QueryFilter.OP_NOT_NULL:
                return property + " IS NOT NULL";
            case QueryFilter.OP_EMPTY:
                return property + " IS EMPTY";
            case QueryFilter.OP_NOT_EMPTY:
                return property + " IS NOT EMPTY";
            case QueryFilter.OP_AND:
            case QueryFilter.OP_OR:
                if (!(value instanceof List)) {
                    return (operator == QueryFilter.OP_AND ? "AND: " : "OR: ") + "**INVALID VALUE - NOT A LIST: (" + value
                            + ") **";
                }

                String op = operator == QueryFilter.OP_AND ? " and " : " or ";

                StringBuilder sb = new StringBuilder("(");
                boolean first = true;
                for (Object o : ((List) value)) {
                    if (first) {
                        first = false;
                    } else {
                        sb.append(op);
                    }
                    if (o instanceof QueryFilter) {
                        sb.append(  this.build((QueryFilter)o, parentParamMap, paramIndex) );
                    } else {
                        sb.append("**INVALID VALUE - NOT A FILTER: (" + o + ") **");
                    }
                }
                if (first) return (operator == QueryFilter.OP_AND ? "AND: " : "OR: ") + "**EMPTY LIST**";

                sb.append(")");
                return sb.toString();
            case QueryFilter.OP_CUSTOM:
                return property;
            default:
                return "**INVALID OPERATOR: (" + operator + ") - VALUE: " + InternalUtil.paramDisplayString(value)
                        + " **";
        }
    }

}
