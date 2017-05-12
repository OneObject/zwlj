package com.zwlj.common.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.zwlj.common.model.BaseModel;
import com.zwlj.common.utils.Page;
import com.zwlj.common.utils.QueryCondition;
import com.zwlj.common.utils.ResultSet;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl<T extends BaseModel<ID>, ID extends Serializable> extends GenericDAOImpl<T, ID> implements BaseDao<T, ID> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public int execute(String hql, Map<String, Object> params) {
        return 0;
    }

    public int execute(String hql, List<Object> params) {
        return 0;
    }

    public T get(ID id) {
        return find(id);
    }

    public ID insert(T entity) {
        save(entity);
        return entity.getId();
    }

    public void update(T entity) {
        save(entity);
    }

    public boolean deleteById(ID id) {
        return removeById(id);
    }

    public boolean delete(T entity) {
        return remove(entity);
    }

    public List<T> list() {
        return findAll();
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
