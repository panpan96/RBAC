package com.juphoon.app.mapper;

import com.juphoon.app.entity.App;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AppMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);

    List<App> getAppList(@Param("userId") Integer userId,@Param("appName") String appName);

    App selectByAppName(@Param("appName")String appName);

}