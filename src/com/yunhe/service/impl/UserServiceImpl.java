package com.yunhe.service.impl;

import com.yunhe.dao.UsersDao;
import com.yunhe.dao.impl.UsersDaoImpl;
import com.yunhe.pojo.Users;
import com.yunhe.service.UsersService;

public class UserServiceImpl implements UsersService {

    UsersDao usersDao = new UsersDaoImpl();
    @Override
    public String res(Users users) {

        String s = usersDao.findUsernameForUsers(users.getUsername());
        if (s != null){

            return "账号已经存在";
        }
        boolean bl = usersDao.formatUsernamePassword(users.getUsername(),users.getPassword());

        if (!bl){
            return "账号或密码格式不对";
        }
        usersDao.addUsers(users);
        return null;
    }

    @Override
    public Users log(Users users) {

        return usersDao.loginForUsers(users.getUsername(),users.getPassword());

    }
}
