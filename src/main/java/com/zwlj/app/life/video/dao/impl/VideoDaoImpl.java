package com.zwlj.app.life.video.dao.impl;

import com.zwlj.app.life.video.dao.VideoDao;
import com.zwlj.app.life.video.model.VideoEntity;
import com.zwlj.common.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("videoDao")
public class VideoDaoImpl extends BaseDaoImpl<VideoEntity, String> implements VideoDao {
}
