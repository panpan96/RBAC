package com.juphoon.app.mapper;

import com.juphoon.app.entity.SysAcl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAclMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);

    int countByAclModuleIdAndName(@Param("aclModuleId") Integer aclModuleId,@Param("name") String name);

    int countByNameAndAclModuleId(@Param("aclModuleId") int aclModuleId, @Param("name") String name, @Param("id") Integer id);

    List<SysAcl> getSysAclList(Integer aclModuleId);

    List<SysAcl> getByIdList(@Param("idList") List<Integer> idList);

    List<SysAcl> getAll();

    int countByAclModuleId(@Param("aclModuleId") int aclModuleId);
}