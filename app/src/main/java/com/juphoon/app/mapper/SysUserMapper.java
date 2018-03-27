package com.juphoon.app.mapper;

import com.juphoon.app.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findByName(String username);

    List<SysUser> getSysUserList();

    List<SysUser> getByIdList(@Param("idList") List<Integer> idList);

    List<SysUser> getAll();

    int countByName(@Param("name") String name);

    void changeUserStatus(@Param("id") Integer id, @Param("status") Integer status, @Param("operator") String operator, @Param("ip") String ip);
}