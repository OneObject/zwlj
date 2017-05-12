package com.zwlj.common.service;

import com.zwlj.common.model.BaseModel;
import com.zwlj.common.utils.Page;
import com.zwlj.common.utils.QueryCondition;
import com.zwlj.common.utils.ResultSet;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T extends BaseModel<ID>, ID extends Serializable> {

    T get(ID id);

    ID insert(T entity);

    void update(T entity);

    void deleteById(ID id);

    void delete(T entity);

    List<T> list();

    List<T> list(Map<String, Object> params);

    <RT> ResultSet<RT> list(Map<String, Object> params, Page page);

    <RT> ResultSet<RT> list(QueryCondition conditions , Page page);

    <RT> ResultSet<RT> list(QueryCondition  conditions );
}
