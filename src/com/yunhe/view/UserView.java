package com.yunhe.view;

import com.yunhe.pojo.Users;
import com.yunhe.pojo.ZhangHu;
import com.yunhe.service.UsersService;
import com.yunhe.service.impl.UserServiceImpl;

import java.util.Scanner;

public class UserView {

    Users users = new Users();

    // 创建一个业务层对象
    UsersService usersService = new UserServiceImpl();
    Scanner sc = new Scanner(System.in);
    ZhangHuView zhv = new ZhangHuView();
    public void menuUsers(){
        System.out.println("菜单页面");

        System.out.println("1：注册 2：登录 0：退出");
        String num = sc.nextLine();
        switch (num){
            case "1":
                userRes();
                break;
            case "2":
                userLogin();
                break;
            case  "0" :
                return;
            default:
                System.out.println("功能暂未开放");
                break;
        }
    }
    /**
     *用户注册
     */
    public void userRes(){
        System.out.println("*********注册页面***********");

        System.out.println("请输入账号： ");
        users.setUsername(sc.nextLine());
        System.out.println("请输入密码： ");
        users.setPassword(sc.nextLine());
        // 把数据传递给业务层
        String user = usersService.res(users);
        System.out.println(user);
    }

    public void userLogin(){
        System.out.println("***********登录页面***********");
        System.out.println("请输入账号： ");
        String name = sc.nextLine();
        users.setUsername(name);
        System.out.println("请输入密码： ");
        users.setPassword(sc.nextLine());

        Users user = usersService.log(users);


        // 登录成功之后进入财务管理界面
        zhv.menuZhangWu(user);
    }


}
