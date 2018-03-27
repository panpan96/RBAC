package com.juphoon.app.interceptor;


import com.juphoon.app.common.Const;
import com.juphoon.app.common.JedisUtils;
import com.juphoon.app.common.JsonUtils;
import com.juphoon.app.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private JedisUtils jedisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //从请求头获取uuid
        String uuid = request.getHeader(Const.TOKEN);
        logger.info("uuid:" + uuid);
        if (uuid == null) {
            response.getWriter().print(JsonUtils.objectToJson(ServerResponse.notLogin()));
            logger.info("用户未登录");
            return false;
        }
        //从redis取登录信息
        String jedisInfo = jedisUtils.getJedis(uuid);
        if (StringUtils.isEmpty(jedisInfo)) {
            response.getWriter().print(JsonUtils.objectToJson(ServerResponse.notLogin()));
            logger.info("用户登录信息过期");
            return false;
        }
        jedisUtils.setToken(uuid, jedisInfo);

        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

}


