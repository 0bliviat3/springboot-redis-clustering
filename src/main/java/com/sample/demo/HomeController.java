package com.sample.demo;

import com.sample.demo.util.RedisUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/home")
@Slf4j
public class HomeController {

    //test 용

    @Autowired
    private  RedisUtil redisUtil;

    @GetMapping("/{hello}")
    public String home(@PathVariable String hello, HttpServletRequest request) {

        String sessionId = new String(Base64
                .getDecoder()
                .decode(request.getCookies()[0].getValue()));

        log.debug(String.format("params = %s", hello));
        log.debug(String.format("sessionID = %s", sessionId));
        Object sessionData = Optional.of(redisUtil.getSessionData(sessionId))
                .orElse(Optional.empty());

        if (!sessionData.equals(Optional.empty())) {
            sessionData = sessionData.toString();
        }

        return String.format("%s 라고함.<br>JSESSIONID: %s<br>sessionAttr:auth: %s",
                hello, sessionId, sessionData);
    }
}
