package com.yunhe.view;

import com.yunhe.pojo.Users;
import com.yunhe.pojo.ZhangHu;
import com.yunhe.service.ZhangHuService;
import com.yunhe.service.impl.ZhangHuServiceImpl;
import java.util.Date;
import java.util.Scanner;

public class ZhangHuView {

    private Users users;
    ZhangHu zh = new ZhangHu();
   static ZhangHuService zhs = new ZhangHuServiceImpl();
    static Scanner sc = new Scanner(System.in);
    static  ZhangHuChildInterface selectZhangHu = new ZhangHuChildInterface();

    public void menuZhangWu(Users users){
        this.users = users;
        while (true) {
            System.out.println("*********账务管理界面***********");
            System.out.println("1.添加账务　2.编辑账务　3.删除账务　4.查询账务　0.退出系统");
            String num = sc.nextLine();
            switch (num){

                case "1":
                    addZhangHu();
                    break;
                case "2":
                    updateZhangHu(users);
                    break;
                case "3":
                    deleteZhangHu();
                    break;
                case "4":
                    selectZhangHu.zhangHuSelect(users);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("输入的格式不对");
                    break;
            }
        }
    }
    //账务添加
    public void addZhangHu(){
        System.out.println("***********添加财务页面**************");
        System.out.println("请输入支出原因");
        zh.setFlname(sc.nextLine());
        System.out.println("请输入剩余金额: ");
        zh.setMoney(Double.valueOf(sc.nextLine()));
        System.out.println("请输入账户: ");
        zh.setZhanghu(sc.nextLine());
        zh.setCreatetime(new Date());
        System.out.println("请输入备注: ");
        zh.setDescription(sc.nextLine());
        System.out.println("请输入用户id: ");
        zh.setUserid(Integer.valueOf(sc.nextLine()));

        String s = zhs.addAccounting(zh);
        System.out.println(s);

    }
    public void updateZhangHu(Users users){
        ZhangHuUpdate zhangHuUpdate = new ZhangHuUpdate();
        zhangHuUpdate.updateAccount(users);

    }
    public void deleteZhangHu(){

    }

}
