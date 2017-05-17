package com.zwlj.app.boke.service;

import com.google.common.collect.Maps;
import com.zwlj.app.boke.model.UserEntity;
import com.zwlj.common.query.Page;
import com.zwlj.common.query.QueryCondition;
import com.zwlj.common.query.Sort;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:com.zwlj.context.xml" })
public class TestUserService {

    @Resource(name = "userService")
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(TestUserService.class);

    @Test
    public void insert() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress("北京西路23");
        userEntity.setAge(10);
        userEntity.setUserName("TTJackTT");
        userEntity.setBathDate(new DateTime(2010, 10, 10, 12, 10).getMillis());
        userService.insert(userEntity);
    }

    @Test
    public void list() {
        System.out.println(userService.list());
    }

    @Test
    public void testQuery() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("userName", "Jack");
        System.out.println(userService.list(params));
    }

    @Test
    public void testQueryFilter() {
        QueryCondition condition = QueryCondition.newInstance();
        condition.addEqualFilter("userName", "Jack");
        condition.addSort(Sort.asc("bathDate"));

        logger.debug("userList[{}]", userService.list(condition, new Page(1, 1)));
    }

    @Test
    public void testQueryResult() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("userName", "Jack");
        params.put("age", 10);
        logger.debug("sql result info: [{}]", userService.list(params, new Page(1, 2)));
        System.out.println("完美测试！");
        System.out.println("冲突");
        // 按apply
    }

}
