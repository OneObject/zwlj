package com.zwlj.app.boke.service;

import com.zwlj.app.boke.model.UserEntity;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:com.zwlj.context.xml" })
public class TestUserService {

    @Resource(name = "userService")
    private UserService userService;

    @Test
    public void insert() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress("北京西路");
        userEntity.setAge(10);
        userEntity.setUserName("Jack");
        userEntity.setBathDate(new DateTime(2010, 10, 10, 12, 10).getMillis());
        userService.insert(userEntity);
    }
}
