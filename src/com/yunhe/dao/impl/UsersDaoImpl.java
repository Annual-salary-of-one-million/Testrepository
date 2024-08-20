package com.yunhe.dao.impl;

import com.yunhe.dao.UsersDao;
import com.yunhe.pojo.Users;
import com.yunhe.pojo.ZhangHu;
import com.yunhe.util.JdbcUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;


public class UsersDaoImpl implements UsersDao {


    JdbcTemplate template= JdbcUtil.template;
    @Override
    public String findUsernameForUsers(String username) {
        String sql = "select password from users where username = ?";


        try {
            return template.queryForObject(sql, new SingleColumnRowMapper<>(String.class), username);
        }catch (EnumConstantNotPresentException e){
            return null;
        }
    }

    @Override
    public void addUsers(Users users) {


        String sql = "insert into users values(null,?,?)";

        int update = template.update(sql, users.getUsername(), users.getPassword());

    }

    @Override
    public Users loginForUsers(String username, String password) {
        String sql = "select * from users where username = ? and password = ?";
        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(Users.class),username,password);

        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    @Override
    public boolean formatUsernamePassword(String username, String password) {
        char c = username.charAt(0);
        if (Character.isLetter(c)&&username.length() >= 8 && username.length() <= 24  ){
            for (int i = 0; i < password.length(); i++) {
                char c1 = password.charAt(i);
                if (!(Character.isLetterOrDigit(c1))){
                    return false;
                }
            }

        }
        return true;
    }

}
