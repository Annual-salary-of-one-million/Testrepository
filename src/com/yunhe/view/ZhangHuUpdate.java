package com.yunhe.view;

import com.yunhe.pojo.Users;
import com.yunhe.pojo.ZhangHu;
import com.yunhe.service.ZhangHuService;
import com.yunhe.service.impl.ZhangHuServiceImpl;

import java.util.Scanner;

public class ZhangHuUpdate {

    private Users users;
    ZhangHu zh = new ZhangHu();
    ZhangHuService zhs = new ZhangHuServiceImpl();
    Scanner sc = new Scanner(System.in);

    public void updateAccount(Users users){
        this.users= users;
        System.out.println("***************编辑账务页面*************");
        System.out.println("1.根据id修改备注 2.根据id修改金额 3. 根据id修改账户信息 4.根据id修改创建时间 0: 返回上一级");
        int num = Integer.valueOf(sc.nextLine());
        switch (num){
            case 1:
                updateDes();
                break;
            case 2:
                updateMoney();
                break;
            case 3:
                updateHu();
                break;
            case 4:
                updateTime();
                break;
            case 0:
                return;
            default:
                System.out.println();
        }
    }
    public void updateDes(){
        System.out.println("请输入要修改的账务id： ");
        int zwid = Integer.valueOf(sc.nextLine());
        System.out.println("请输入新内容： ");
        String des = sc.nextLine();

        zhs.updateZhangHu(des,zwid,users.getId());

    }

    //根据账务id 修改当前用户的账务金额
    public void updateMoney(){
        System.out.println("输入要修改的金额");
        double money = Double.valueOf(sc.nextLine());
        System.out.println("输入要修改的账务id：");
        int zwid = Integer.valueOf(sc.nextLine());

        zhs.updateZhangHuManey(money,zwid,users.getId());

    }
    //根据账务id 修改当前用户的账务账户
    public void updateHu(){
        System.out.println("输入要修改为：");
        String hu = sc.nextLine();
        System.out.println("输入要修改的账务id：");
        int zwid = Integer.valueOf(sc.nextLine());

        zhs.updateHu(hu,zwid,users.getId());
    }

    //根据账务id 修改当前用户的账务创建时间
    public void updateTime(){
        System.out.println("输入要修改后的时间： ");
        String time = sc.nextLine();
        System.out.println("输入要修改的账务id：");
        int zwid = Integer.valueOf(sc.nextLine());

        zhs.updateTime(time,zwid,users.getId());
    }
}
