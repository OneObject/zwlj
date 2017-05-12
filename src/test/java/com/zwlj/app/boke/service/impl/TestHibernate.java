package com.zwlj.app.boke.service.impl;

import com.zwlj.app.boke.service.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:com.zwlj.context.xml" })
public class TestHibernate {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void list() {
        // 使用 open session() 获取数据库的session
        Session session = sessionFactory.openSession();
        UserEntity user = session.get(UserEntity.class, 1l);
        System.out.println(user);
        session.close();
    }
}
