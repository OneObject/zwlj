package com.zwlj.common.dao;

import com.zwlj.common.model.BaseModel;
import com.zwlj.common.utils.Page;
import com.zwlj.common.utils.QueryCondition;
import com.zwlj.common.utils.QueryFilter;
import com.zwlj.common.utils.ResultSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Transactional
public class BaseDaoImpl<T extends BaseModel<ID>, ID extends Serializable> implements BaseDao<T, ID> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<T> entityClass;

    protected SessionFactory sessionFactory;

    public BaseDaoImpl(SessionFactory sessionFactory) {
        Type type = getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
        entityClass = (Class<T>) types[0];

        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public int execute(String hql, Map<String, Object> params) {
        return 0;
    }

    public int execute(String hql, List<Object> params) {
        return 0;
    }

    public T get(ID id) {
        if(id != null) {
            return getCurrentSession().find(entityClass, id);
        }
        return null;
    }

    public ID insert(T entity) {
        // 如果传递进persist()方法的参数不是实体Bean，会引发IllegalArgumentException
        getCurrentSession().persist(entity);
        return entity.getId();
    }

    public void update(T entity) {
        Session session = getCurrentSession();
        if(session.contains(entity)) {
            session.refresh(entity);
        } else {
            session.merge(entity);
            session.refresh(entity);
        }
    }

    public void deleteById(ID id) {
        Session session = getCurrentSession();
        T entity = session.find(entityClass, id);
        session.remove(entity);
    }

    public void delete(T entity) {
        getCurrentSession().remove(entity);
    }

    public List<T> list() {
        Session session = getCurrentSession();
        String hql = String.format("from %s", entityClass.getName());
        Query<T> query = session.createQuery(hql);
        return query.list();
    }

    public List<T> list(Map<String, Object> params) {
        Session session = getCurrentSession();

        StringBuilder hql = new StringBuilder("from ");
        hql.append(entityClass.getName());
        this.hqlAddParams(hql, params);

        Query<T> query = session.createQuery(hql.toString());
        this.queryAddParams(query, params);

        return query.list();
    }

    public <RT> ResultSet<RT> list(Map<String, Object> params, Page page) {
        Session session = getCurrentSession();

        StringBuilder hql = new StringBuilder(" from ");
        hql.append(entityClass.getName());
        this.hqlAddParams(hql, params);

        // 查询列表
        Query<RT> query = session.createQuery(hql.toString());
        this.queryAddParams(query, params);
        query.setFirstResult((page.getCurrent_page() - 1) * page.getPage_size());
        query.setMaxResults(page.getPage_size());
        List<RT> list = query.list();

        return new ResultSet<RT>(list, page.getPage_size(), page.getCurrent_page(), count(hql, params));
    }

    public <RT> ResultSet<RT> list(QueryCondition conditions, Page page) {
        return null;
    }

    public <RT> ResultSet<RT> list(QueryCondition conditions) {
        return null;
    }

    private void hqlAddParams(StringBuilder hql, Map<String, Object> params) {
        if(params != null && params.size() > 0) {
            hql.append(" where ");
            boolean first = true;
            for (String key : params.keySet()) {
                if (first) {
                    first = false;
                } else {
                    hql.append(" and ");
                }
                hql.append(key);
                hql.append(" = :");
                hql.append(key);
            }
        }
    }

    private void addLimit(StringBuilder hql, Map<String, Object> params) {

    }

    private void queryAddParams(Query query, Map<String, Object> params) {
        if(params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
    }

    private long count(StringBuilder hql, Map<String, Object> params) {
        Session session = getCurrentSession();
        hql.insert(0, " select count(*) ");

        Query<Long> query = session.createQuery(hql.toString());
        queryAddParams(query, params);
        return query.getSingleResult();
    }
}
