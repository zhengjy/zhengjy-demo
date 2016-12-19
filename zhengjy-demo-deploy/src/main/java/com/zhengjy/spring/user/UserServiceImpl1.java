package com.zhengjy.spring.user;

import org.springframework.stereotype.Service;

/**
 * Created by zhengjy on 2016/12/19.
 */
@Service
public class UserServiceImpl1 implements UserService{
    @Override
    public String getName() {
        return "UserServiceImpl1.getName()";
    }
}
