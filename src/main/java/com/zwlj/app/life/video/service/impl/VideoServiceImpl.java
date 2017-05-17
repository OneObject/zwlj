package com.zwlj.app.life.video.service.impl;

import com.zwlj.app.life.video.dao.VideoDao;
import com.zwlj.app.life.video.model.VideoEntity;
import com.zwlj.app.life.video.service.VideoService;
import com.zwlj.common.dao.BaseDao;
import com.zwlj.common.query.Page;
import com.zwlj.common.query.ResultSet;
import com.zwlj.common.service.BaseService5HImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("videoService")
public class VideoServiceImpl extends BaseService5HImpl<VideoEntity, String> implements VideoService {

    @Resource(name = "videoDao")
    private VideoDao videoDao;

    public BaseDao<VideoEntity, String> getDAO() {
        return videoDao;
    }

    public ResultSet<VideoEntity> all(Page page) {
        return null;
    }
}
