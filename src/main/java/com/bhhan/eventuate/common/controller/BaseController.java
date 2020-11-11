package com.bhhan.eventuate.common.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */
public class BaseController {
    protected <T> T withRequestAttributeContext(HttpServletRequest request, Callable<T> runnable){
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        try {
            return runnable.call();
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }
}
