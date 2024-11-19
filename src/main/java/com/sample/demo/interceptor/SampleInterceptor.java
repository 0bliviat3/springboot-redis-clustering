package com.sample.demo.interceptor;

import com.sample.demo.sign.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class SampleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("===========preHandle===========");
        log.debug(request.getSession().getId());
        HttpSession session = request.getSession();
        Object member = session.getAttribute("auth");

        String root = request.getContextPath();

        if (member == null) {
            response.sendRedirect(String.format("%s/sign/in-page", root));
        }

        //log.debug(session.getAttribute("auth").toString());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("===========postHandle===========");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
