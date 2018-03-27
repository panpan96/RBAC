package com.juphoon.app.controller;

import com.github.pagehelper.PageInfo;
import com.juphoon.app.common.JedisUtils;
import com.juphoon.app.common.JsonUtils;
import com.juphoon.app.entity.vo.UserVo;
import com.juphoon.app.exception.BusinessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by ztf on 2018/2/5
 */
public class BaseController {


    protected Map<String, Object> getTables(PageInfo pageInfo) {
        Map<String, Object> map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("list", pageInfo.getList());
        return map;
    }

    protected Map<String, Object> getZeroTables() {
        Map<String, Object> map = new HashMap();
        map.put("total", 0);
        map.put("list", Collections.emptyList());
        return map;
    }

    /**
     * 得到request对象
     */
    protected HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        return request;
    }

    protected String getUUID() {
        return getRequest().getHeader("token");
    }

    protected Integer getUserId(JedisUtils jedisUtils) {
        UserVo user = getUser(jedisUtils);
        if(user == null)
            throw new BusinessException("登录信息失效");
        return user.getId();
    }

    protected UserVo getUser(JedisUtils jedisUtils) {
        String userJson = jedisUtils.getJedis(getUUID());
        return JsonUtils.jsonToPojo(userJson, UserVo.class);
    }

    /**
     * 框架校验bean的错误信息
     * @param results
     * @return
     */
    protected String vailDataErrorMessage(BindingResult results) {

        Map<String, String> map = vailDataErrorMessageMap(results);
        StringBuffer sb = new StringBuffer();
        Collection<String> values = map.values();
        for (String s: values) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }

    /**
     * 框架校验bean的错误信息
     * 返回 map中key为bean字段 value为错误信息
     * @param results
     * @return
     */
    protected Map<String, String> vailDataErrorMessageMap(BindingResult results) {
        List<FieldError> fieldErrors = results.getFieldErrors();

        Map<String, String> map = new LinkedHashMap<String, String>();
        for(FieldError error: fieldErrors){
            if (map.get(error.getField()) != null ) {
                // 有多个验证注解时 默认先取 @NotBlank @NotEmpty
                if("NotBlank".equals(error.getCode()) || "NotEmpty".equals(error.getCode())) {
                    map.put(error.getField(), error.getDefaultMessage());
                } else {
                    continue;
                }
            }

            map.put(error.getField(), error.getDefaultMessage());
        }

        return map;
    }

}
