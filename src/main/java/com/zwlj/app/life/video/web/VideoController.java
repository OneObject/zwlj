package com.zwlj.app.life.video.web;

import com.zwlj.app.life.video.model.VideoEntity;
import com.zwlj.app.life.video.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("videoController")
@RequestMapping("/video/")
public class VideoController {

    private Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Resource(name = "videoService")
    private VideoService videoService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        logger.debug("index");
        return "video/index";
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public void save(HttpServletRequest request) {
        String title = request.getParameter("title");
        String videoUrl = request.getParameter("videoUrl");
        String content = request.getParameter("content");
        String cover = request.getParameter("cover");

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setTitle(title);
        videoEntity.setVideoUrl(videoUrl);
        videoEntity.setContent(content);
        videoEntity.setCover(cover);
        videoService.insert(videoEntity);
    }

}
