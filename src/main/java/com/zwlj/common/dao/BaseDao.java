package com.zwlj.common.dao;

import com.zwlj.common.model.BaseModel;
import com.zwlj.common.query.Page;
import com.zwlj.common.query.ResultSet;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseModel<ID>, ID extends Serializable> {

    int execute(String hql, Map<String, Object> params);

    int execute(String hql, List<Object> params);

    T get(ID id);

    ID insert(T entity);

    void update(T entity);

    void deleteById(ID id);

    void delete(T entity);

    List<T> list();

    List<T> list(Map<String, Object> params);

    <RT> ResultSet<RT> list(Map<String, Object> params, Page page);

}
