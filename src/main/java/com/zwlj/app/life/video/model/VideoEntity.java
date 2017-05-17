package com.zwlj.app.life.video.model;

import com.zwlj.common.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "life_video")
public class VideoEntity extends BaseModel<String> {

    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid")
    @GeneratedValue(generator = "idGen")
    private String id;

    private String title;   //标题

    private String videoUrl;    //视频地址

    private String localUrl;    //本地地址

    private String content;     //信息

    private boolean uploadLocal = false;    //是否下载到本地

    private long createTime = System.currentTimeMillis();    //创建时间

    private long updateTime;    //修改时间

    @Override
    public String toString() {
        return "VideoEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", localUrl='" + localUrl + '\'' +
                ", content='" + content + '\'' +
                ", uploadLocal=" + uploadLocal +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isUploadLocal() {
        return uploadLocal;
    }

    public void setUploadLocal(boolean uploadLocal) {
        this.uploadLocal = uploadLocal;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
