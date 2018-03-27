package com.juphoon.app.mapper;


import com.juphoon.app.entity.User;
import org.apache.ibatis.annotations.Param;



public interface UserMapper {


    User findByName(@Param("username") String username);


}