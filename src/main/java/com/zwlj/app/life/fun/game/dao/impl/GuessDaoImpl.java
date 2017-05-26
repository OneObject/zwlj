package com.zwlj.app.life.fun.game.dao.impl;

import com.zwlj.app.life.fun.game.dao.GuessDao;
import com.zwlj.app.life.fun.game.model.GuessModel;
import com.zwlj.common.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("guessDao")
public class GuessDaoImpl extends BaseDaoImpl<GuessModel, Long> implements GuessDao {
}
