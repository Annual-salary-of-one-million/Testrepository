package com.yunhe.dao;

import com.yunhe.pojo.Users;

public interface UsersDao {
    String findUsernameForUsers(String username);



    void addUsers(Users users);

    Users loginForUsers(String username, String password);

    boolean formatUsernamePassword(String username, String password);

}
