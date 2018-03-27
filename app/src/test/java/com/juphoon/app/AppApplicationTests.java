package com.juphoon.app;

import com.alibaba.fastjson.JSONObject;
import com.github.stuxuhai.jpinyin.PinyinException;

import com.juphoon.app.common.IpUtil;
import com.juphoon.app.common.JedisUtils;

import com.juphoon.app.mapper.SysAclModuleMapper;
import com.juphoon.app.service.SysAclModuleService;
import com.juphoon.app.service.SysTreeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTests {



	@Autowired
	private SysTreeService sysTreeService;

	@Autowired
	private SysAclModuleMapper sysAclModuleMapper;
	@Test
	public void contextLoads() {

	//	System.out.println(appMapper.selectByPrimaryKey(2));
		System.out.println(sysAclModuleMapper.getChildAclModuleListByLevel("0.6"));
		//System.out.println(sysTreeService.roleTree(4,1));


		String uuid = UUID.randomUUID().toString();    //获取UUID并转化为String对象
		uuid = uuid.replace("-", "");


		//System.out.println(IpUtil.getRemoteIp(request));
	}

}
