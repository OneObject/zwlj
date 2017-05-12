package com.zwlj.app.boke.service.impl;

import com.zwlj.app.boke.dao.UserDao;
import com.zwlj.app.boke.model.UserEntity;
import com.zwlj.app.boke.service.UserService;
import com.zwlj.common.dao.BaseDao;
import com.zwlj.common.service.BaseService5HImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl extends BaseService5HImpl<UserEntity, Long> implements UserService{

    @Resource(name = "userDao")
    private UserDao userDao;

    public BaseDao<UserEntity, Long> getDAO() {
        return userDao;
    }

}
