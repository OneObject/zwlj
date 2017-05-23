package com.zwlj.common.utils;

/**
 * hibernate主键生成策略
 */
public class HibernateStrategy {

    /**
     * Hibernate中唯一一种最简单通用的主键生成器就是uuid。
     * 虽然是个32位难读的长字符串，但是它没有跨数据库的问题，将来切换数据库极其简单方便，推荐使用！
     */
    public static final String UUID = "uuid";
}
