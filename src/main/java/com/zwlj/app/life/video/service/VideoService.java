package com.zwlj.app.life.video.service;

import com.zwlj.app.life.video.model.VideoEntity;
import com.zwlj.common.query.Page;
import com.zwlj.common.query.ResultSet;
import com.zwlj.common.service.BaseService;

public interface VideoService extends BaseService<VideoEntity, String> {

    ResultSet<VideoEntity> all(Page page);

}
