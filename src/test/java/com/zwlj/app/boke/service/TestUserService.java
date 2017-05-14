package com.zwlj.app.boke.service;

import com.google.common.collect.Maps;
import com.zwlj.app.boke.model.UserEntity;
import com.zwlj.common.utils.Page;
import com.zwlj.common.utils.QueryCondition;
import com.zwlj.common.utils.QueryFilter;
import com.zwlj.common.utils.Sort;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:com.zwlj.context.xml" })
public class TestUserService {

    @Resource(name = "userService")
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(TestUserService.class);


    public void insert() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress("北京西路");
        userEntity.setAge(10);
        userEntity.setUserName("Jack");
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
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.addFilterEqual("name", "Jack");
        queryCondition.addFilterEqual("aa", "bb");
        queryCondition.addFilterEqual("cc", 22);
        queryCondition.addFilter(new QueryFilter("ss", 22, QueryFilter.OP_AND));
        queryCondition.addSort(new Sort("createtime", Sort.Order.ASC));

        for (QueryFilter filter : queryCondition.getFilters()) {
            logger.debug("sql where info: {}", filter);
        }
        queryCondition.getSort();
    }

    @Test
    public void testQueryResult() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("userName", "Jack");
        params.put("age", 10);
        logger.debug("sql result info: [{}]", userService.list(params, new Page(1)));
    }
}
