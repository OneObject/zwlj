package com.zwlj.app.life.fun.game.web;

import com.zwlj.app.life.fun.game.model.GuessModel;
import com.zwlj.app.life.fun.game.service.GuessService;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("guessController")
@RequestMapping("/fun/game/guess/")
public class GuessController {

    private Logger logger = LoggerFactory.getLogger(GuessController.class);

    @Resource(name = "guessService")
    private GuessService guessService;

    @RequestMapping("index")
    public String index() {

        return "fun/game/guess/index";
    }

    @RequestMapping("guess")
    @ResponseBody
    public int guess(HttpServletRequest request) {
        String id = request.getParameter("id");
        int number = NumberUtils.toInt(request.getParameter("number"));

        HttpSession session = request.getSession();
        Integer sysNum = (Integer) session.getAttribute(id);
        if (sysNum == null) {
            // 数字不存在
            return 2;
        }
        int result = sysNum.compareTo(number);

        GuessModel guessModel = new GuessModel();
        guessModel.setClientIp(request.getRemoteAddr());
        guessModel.setSysNum(sysNum);
        guessModel.setCreateSysTime(NumberUtils.toLong(id));
        guessModel.setTime(1);
        guessModel.setGuessNum(number);
        guessService.insert(guessModel);

        if (result == 0) {
            session.removeAttribute(id);
        }

        return result;
    }

    @RequestMapping("random")
    @ResponseBody
    public String random(HttpSession session) {
        // 系统生成随机数
        String id = System.currentTimeMillis() + "";
        int random = (int) (Math.random() * 100);
        session.setAttribute(id, random);
        return id;
    }

}
