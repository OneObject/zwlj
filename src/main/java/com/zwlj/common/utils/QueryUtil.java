package com.zwlj.common.utils;

import com.zwlj.common.query.Page;

public class QueryUtil {

    public static int getFirstResult(Page page) {
        if(page != null) {
            return (page.getCurrent_page() - 1) * page.getPage_size();
        }
        return 0;
    }

    public static int getMaxResult(Page page) {
        if (page != null) {
            return page.getPage_size();
        }
        return 0;
    }

}

