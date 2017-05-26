package com.zwlj.app.life.fun.game.model;

import com.zwlj.common.model.BaseModel;
import com.zwlj.common.utils.HibernateStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "life_fun_guess")
public class GuessModel extends BaseModel<Long> {

    @Id
    @GenericGenerator(name = "idGen", strategy = HibernateStrategy.IDENTITY)
    @GeneratedValue(generator = "idGen")
    private Long id;

    private String clientIp;    // 客户端ip

    private int guessNum;    // 猜测的数字

    private int sysNum;    //系统生成的数字

    private int time = 1;   // 猜测的次数

    private long createTime;    //创建时间

    private long createSysTime;     //系统生成数字的时间与数字组成唯一标识

    @Override
    public String toString() {
        return "GuessModel{" +
                "id=" + id +
                ", clientIp='" + clientIp + '\'' +
                ", guessNum=" + guessNum +
                ", sysNum=" + sysNum +
                ", time=" + time +
                ", createTime=" + createTime +
                ", createSysTime=" + createSysTime +
                "} " + super.toString();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public int getGuessNum() {
        return guessNum;
    }

    public void setGuessNum(int guessNum) {
        this.guessNum = guessNum;
    }

    public int getSysNum() {
        return sysNum;
    }

    public void setSysNum(int sysNum) {
        this.sysNum = sysNum;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getCreateSysTime() {
        return createSysTime;
    }

    public void setCreateSysTime(long createSysTime) {
        this.createSysTime = createSysTime;
    }
}
