package com.zwlj.app.life.fun.game.web;

import com.zwlj.app.life.fun.game.service.GuessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller("guessController")
@RequestMapping("/fun/game/guess/")
public class GuessController {

    private Logger logger = LoggerFactory.getLogger(GuessController.class);

    @Resource(name = "guessService")
    private GuessService guessService;

    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        // 获取系统时间
        System.currentTimeMillis();

        // 获取session
        HttpSession session = request.getSession();
        session.setAttribute("", Math.random() * 100);

        return "fun/game/guess/index";
    }

}
