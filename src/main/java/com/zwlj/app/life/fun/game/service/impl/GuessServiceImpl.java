package com.zwlj.app.life.fun.game.service.impl;

import com.zwlj.app.life.fun.game.dao.GuessDao;
import com.zwlj.app.life.fun.game.model.GuessModel;
import com.zwlj.app.life.fun.game.service.GuessService;
import com.zwlj.common.dao.BaseDao;
import com.zwlj.common.service.BaseService5HImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("guessService")
public class GuessServiceImpl extends BaseService5HImpl<GuessModel, Long> implements GuessService {

    @Resource(name = "guessDao")
    private GuessDao guessDao;

    public BaseDao<GuessModel, Long> getDAO() {
        return guessDao;
    }
}
