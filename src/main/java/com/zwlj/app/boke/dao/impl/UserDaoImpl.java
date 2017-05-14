package com.zwlj.app.boke.dao.impl;

import com.zwlj.app.boke.dao.UserDao;
import com.zwlj.app.boke.model.UserEntity;
import com.zwlj.common.dao.BaseDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserEntity, Long> implements UserDao {

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
