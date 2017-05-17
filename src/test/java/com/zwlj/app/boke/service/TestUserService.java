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
        // 按apply，left apply 是接受你的，riri是选择你的版本，中间的是让我手动选择那些要那些不要的，选择之后apply，是不是！对对
        //若果我直接APPLY，选择的是什么？中间的？对，apply就是中间的版本，明白了，若果三个人修改了，是不是再增加那个人的版本？不会
        // right 是最新版本，你之前怎么push的？对，不是先commit在push么
        //直接push没有选择的啊，就是先commit然后再push,比如我今天早上来，我是先pull吧，随便
        //哦哦，明白，反正冲突的话，可以选择，没错。这个太屌了，哈哈，必须的，这个号
        //以后随时写，随时提交，不用带U盘了没错，但是所有人都可以看到，对啊，都能看你的操作，写的啥，修改了啥，不能写隐私
        //不能写保密项目就是了对的，无所谓，现在我不写那个了，现在的不保密了么，现在的项目是不保密的，也是内网，不过不是公安网
        //
    }

}
