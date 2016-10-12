package com.tlc.marketing.interceptor;

import com.tlc.marketing.utils.CHECKMSG;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@author chepeiqing
 *@Mail chepeiqin@icloud.com
 *@Date 16/10/12
 *@Time 下午10:16
 *@version V1.0.0
 */
public class RoleInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(RoleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String action = request.getRequestURL().toString();
        if(action.indexOf("login.do")>0){
            return true;
        }else{
            Object obj = request.getSession().getAttribute("_USER");
            if(obj == null){
                throw new RuntimeException(CHECKMSG.PLEASE_LOG_IN_AGAIN);
            }
        }
        logger.debug("TransAction:==>"+action);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
