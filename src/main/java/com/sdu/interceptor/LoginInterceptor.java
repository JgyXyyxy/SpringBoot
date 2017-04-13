package com.sdu.interceptor;

import com.sdu.bean.SessionData;
import com.sdu.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

import static com.sdu.util.Constants.MOBILE_NUMBER_SESSION_KEY;
import static com.sdu.util.Constants.SESSION_KEY;
import static com.sdu.util.Constants.USER_CODE_SESSION_KEY;

/**
 * Created by J on 2017/3/3.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{
    @Autowired
    private RedisUtils redisUtils;
    private final static String SESSION_KEY_PREFIX = "session";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!handler.getClass().isAssignableFrom(HandlerMethod.class)){
            return  true;
        }
        handlerSession(request);

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        if (clazz.isAnnotationPresent(Auth.class)){
            if(request.getAttribute(USER_CODE_SESSION_KEY) == null){
                throw new Exception();
            }else{
                return true;
            }
        }
        return true;
    }

    public void handlerSession(HttpServletRequest request){
        String sessionId = request.getHeader(SESSION_KEY);
        if(StringUtils.isNotBlank(sessionId)){
            SessionData model = (SessionData) redisUtils.get(SESSION_KEY_PREFIX+sessionId);
            if(model == null){
                return;
            }
            request.setAttribute(SESSION_KEY,sessionId);
            Integer userCode = model.getUserCode();
            if(userCode != null){
                request.setAttribute(USER_CODE_SESSION_KEY,Long.valueOf(userCode));
            }
            String mobile = model.getMobileNumber();
            if(mobile != null){
                request.setAttribute(MOBILE_NUMBER_SESSION_KEY,mobile);

            }
        }
        return;
    }
}
