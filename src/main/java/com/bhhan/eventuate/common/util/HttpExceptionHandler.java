package com.bhhan.eventuate.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@ControllerAdvice
@Slf4j
public class HttpExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
