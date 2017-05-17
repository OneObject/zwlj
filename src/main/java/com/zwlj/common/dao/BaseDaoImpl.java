package com.zwlj.common.dao;

import com.zwlj.common.model.BaseModel;
import com.zwlj.common.query.Page;
import com.zwlj.common.utils.QueryUtil;
import com.zwlj.common.query.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Transactional
public class BaseDaoImpl<T extends BaseModel<ID>, ID extends Serializable> implements BaseDao<T, ID> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<T> entityClass;

    @PersistenceContext
    protected EntityManager manager;

    public BaseDaoImpl() {
        Type type = getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
        entityClass = (Class<T>) types[0];
    }

    public int execute(String hql, Map<String, Object> params) {
        return 0;
    }

    public int execute(String hql, List<Object> params) {
        return 0;
    }

    public T get(ID id) {
        if(id != null) {
            return manager.find(entityClass, id);
        }
        return null;
    }

    public ID insert(T entity) {
        // 如果传递进persist()方法的参数不是实体Bean，会引发IllegalArgumentException
        manager.persist(entity);
        return entity.getId();
    }

    public void update(T entity) {
        if(manager.contains(entity)) {
            manager.refresh(entity);
        } else {
            manager.merge(entity);
            manager.refresh(entity);
        }
    }

    public void deleteById(ID id) {
        T entity = manager.find(entityClass, id);
        manager.remove(entity);
    }

    public void delete(T entity) {
        manager.remove(entity);
    }

    public List<T> list() {
        String hql = String.format("from %s", entityClass.getName());
        TypedQuery<T> query = (TypedQuery<T>) manager.createQuery(hql);
        return query.getResultList();
    }

    public List<T> list(Map<String, Object> params) {
        StringBuilder hql = new StringBuilder("from ");
        hql.append(entityClass.getName());
        this.hqlAddParams(hql, params);

        TypedQuery<T> query = (TypedQuery<T>) manager.createQuery(hql.toString());
        this.queryAddParams(query, params);

        return query.getResultList();
    }

    public <RT> ResultSet<RT> list(Map<String, Object> params, Page page) {
        StringBuilder hql = new StringBuilder(" from ");
        hql.append(entityClass.getName());
        this.hqlAddParams(hql, params);

        // 查询列表
        TypedQuery<RT> query = (TypedQuery<RT>) manager.createQuery(hql.toString());
        this.queryAddParams(query, params);
        this.queryAddPaging(query, page);
        List<RT> list = query.getResultList();

        return new ResultSet<RT>(list, page.getPage_size(), page.getCurrent_page(), count(hql, params));
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

    private void queryAddParams(Query query, Map<String, Object> params) {
        if(params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
    }

    private void queryAddPaging(Query query, Page page) {
        int firstResult = QueryUtil.getFirstResult(page);
        int maxResult = QueryUtil.getMaxResult(page);
        if (firstResult > 0) {
            query.setFirstResult(firstResult);
        }
        if (maxResult > 0) {
            query.setMaxResults(maxResult);
        }
    }

    private long count(StringBuilder hql, Map<String, Object> params) {
        hql.insert(0, " select count(*) ");

        Query query = manager.createQuery(hql.toString());
        queryAddParams(query, params);
        return (Long) query.getSingleResult();
    }
}
