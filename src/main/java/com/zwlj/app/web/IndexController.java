package com.zwlj.app.web;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = {"/", "index"})
    public String index(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            name = "aa";
        }
        Data data = new Data(name);
        data.setAction("login");
        data.setResult("success");
        data.print();

        response.addCookie(new Cookie("uid", "124aaff65874eeds"));
        response.addCookie(new Cookie("domain", "ajy"));
        return "index";
    }

    class Data {
        private Logger logger = LoggerFactory.getLogger(Data.class);

        private String who;

        private String date;

        private String action;

        private String result;

        Data(String who) {
            this.who = who;
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss,SSS");
            date = formatter.print(System.currentTimeMillis());
        }

        void print() {
            logger.info(JSON.toJSONString(this));
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}

