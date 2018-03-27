package com.juphoon.app.service.impl;

import com.juphoon.app.common.Md5Encrypt;
import com.juphoon.app.entity.User;
import com.juphoon.app.exception.BusinessException;
import com.juphoon.app.mapper.UserMapper;
import com.juphoon.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    public User validate(String username, String intPassword){

        User u = userMapper.findByName(username);
        if(u == null){
            throw new BusinessException("用户名不存在");
        }
        String password = u.getPassword();
        if(Md5Encrypt.string2MD5(intPassword).equals(password)){
            return u;
        }
        throw new BusinessException("密码错误");
    }
}
