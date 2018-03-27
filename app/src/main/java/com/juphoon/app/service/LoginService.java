package com.juphoon.app.service;


import com.juphoon.app.entity.User;

public interface LoginService {

    User validate(String username, String intPassword);

}
