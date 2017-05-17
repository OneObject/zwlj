package com.zwlj.app.boke.dao.impl;

import com.zwlj.app.boke.dao.UserDao;
import com.zwlj.app.boke.model.UserEntity;
import com.zwlj.common.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserEntity, Long> implements UserDao {

}
