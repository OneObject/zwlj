package com.zwlj.common.service;

import com.zwlj.common.dao.BaseDao;
import com.zwlj.common.model.BaseModel;
import com.zwlj.common.utils.Page;
import com.zwlj.common.utils.QueryCondition;
import com.zwlj.common.utils.ResultSet;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseService5HImpl<T extends BaseModel<ID>, ID extends Serializable> implements BaseService<T, ID> {

    public abstract BaseDao<T, ID> getDAO();

    public T get(ID id) {
        return null;
    }

    public ID insert(T entity) {
        return null;
    }

    public void update(T entity) {

    }

    public void deleteById(ID id) {

    }

    public void delete(T entity) {

    }

    public List<T> list() {
        return null;
    }

    public List<T> list(Map<String, Object> params) {
        return null;
    }

    public <RT> ResultSet<RT> list(Map<String, Object> params, Page page) {
        return null;
    }

    public <RT> ResultSet<RT> list(QueryCondition conditions, Page page) {
        return null;
    }

    public <RT> ResultSet<RT> list(QueryCondition conditions) {
        return null;
    }
}
