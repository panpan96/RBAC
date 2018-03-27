package com.juphoon.app.controller;



import com.juphoon.app.common.ServerResponse;

import com.juphoon.app.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by ztf on 2018/1/22
 */
@ControllerAdvice
public class AppControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    /**
     * 错误处理器
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ServerResponse handleIOException(HttpServletRequest request,Exception e) {
        logger.info(e.toString());
        System.out.println(e.getMessage());
        logger.info(request.getRequestURI());
        logger.error("exception", e);

      if (e instanceof BusinessException) {
           BusinessException b = (BusinessException) e;
           return ServerResponse.error(b.getMessage());
       }
      return  null;
    }
}
