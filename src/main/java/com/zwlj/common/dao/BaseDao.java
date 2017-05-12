package com.zwlj.common.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.zwlj.common.model.BaseModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseModel<ID>, ID extends Serializable> extends GenericDAO<T, ID> {

    int execute(String hql, Map<String, Object> params);

    int execute(String hql, List<Object> params);

    T get(ID id);

    ID insert(T entity);

    void update(T entity);

    boolean deleteById(ID id);

    boolean delete(T entity);

    List<T> list();

}
