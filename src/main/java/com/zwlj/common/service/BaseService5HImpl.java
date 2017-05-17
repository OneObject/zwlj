package com.zwlj.common.service;

import com.zwlj.common.dao.BaseDao;
import com.zwlj.common.model.BaseModel;
import com.zwlj.common.query.Page;
import com.zwlj.common.query.ResultSet;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseService5HImpl<T extends BaseModel<ID>, ID extends Serializable> implements BaseService<T, ID> {

    public abstract BaseDao<T, ID> getDAO();

    public T get(ID id) {
        return this.getDAO().get(id);
    }

    public ID insert(T entity) {
        return this.getDAO().insert(entity);
    }

    public void update(T entity) {
        this.getDAO().update(entity);
    }

    public void deleteById(ID id) {
        this.getDAO().deleteById(id);
    }

    public void delete(T entity) {
        this.getDAO().delete(entity);
    }

    public List<T> list() {
        return this.getDAO().list();
    }

    public List<T> list(Map<String, Object> params) {
        return this.getDAO().list(params);
    }

    public <RT> ResultSet<RT> list(Map<String, Object> params, Page page) {
        return this.getDAO().list(params, page);
    }

}
