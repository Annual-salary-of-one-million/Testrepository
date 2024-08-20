package com.yunhe.view;

import com.yunhe.pojo.Users;
import com.yunhe.pojo.ZhangHu;
import com.yunhe.service.ZhangHuService;
import com.yunhe.service.impl.ZhangHuServiceImpl;
import com.yunhe.util.DateUtil;

import java.util.*;

public class ZhangHuChildInterface {

    private Users users;
    ZhangHu zh = new ZhangHu();
    ZhangHuService zhs = new ZhangHuServiceImpl();
    Scanner sc = new Scanner(System.in);

    public void zhangHuSelect(Users users){
        this.users = users;

        System.out.println("***********欢迎进行账户查询界面************");

        System.out.println("1：查询当前用户的所有信息 2：根据起始日期和结束日期查询 3：根据账户查询 4：根据类型查询 5: 根据金额范围账户信息 6:查询当前用户支付账务最多的账户名称，支付次数  7: 分页查询 0：退出查询界面");
        String num = sc.nextLine();

        switch (num){
            case "1":
                findByUserid();
                break;
            case "2":
                findByDate();
                break;
            case "3":
               findByZhangHu();
                break;
            case "4":
                findByFlname();
                break;
            case "5":
                findByMoney();
                break;
            case "7":
                findByLimit();
                break;

            case "0":
                return;
            default:
                System.out.println("功能还未开放");
        }
    }
    //分页查询
    private void findByLimit() {
        //获取刚进去第一页
        LimitCount(users.getId(),1);

        System.out.println("请输入页码: ");
        int pageNum = Integer.valueOf(sc.nextLine());
        LimitCount(users.getId(),pageNum);

    }

    private void LimitCount(int userid,int pageNum){
        //调用业务层
        Map<String, Object> maps = zhs.AccouningForLimit(users.getId(),pageNum);

        for (ZhangHu zhanghu : (List<ZhangHu>)maps.get("zhanghuList")) {
            System.out.println("类型："+zhanghu.getFlname()+"账户："+zhanghu.getZhanghu()+"时间："+zhanghu.getCreatetime());
        }
        System.out.println("当前第"+maps.get("pageNum")+"页"+","+"共"+maps.get("pageCount")+"页");

    }

    //根据金额范围进行查询
    private void findByMoney() {

        System.out.println("输入最大金额：");
        Double maxMoney = Double.valueOf(sc.nextLine());
        System.out.println("输入最小金额");
        Double minMoney = Double.valueOf(sc.nextLine());

        List<ZhangHu> list = zhs.findAccouningByMony(minMoney,maxMoney,users.getId());
        //遍历集合
        for (ZhangHu zhangHu : list) {
            System.out.println("账户类型："+ zhangHu.getFlname()+"账户余额： " +zhangHu.getMoney() + "账户："+zhangHu.getZhanghu()+"日期："+ DateUtil.dateFormat(zhangHu.getCreatetime(),"YYYY-MM-dd"+"备注: "+zhangHu.getDescription()));
        }
    }
    //根据支出类型查询
    private void findByFlname() {
        System.out.println("请输入支出类型");
        String type = sc.nextLine();

        //接收业务层返回的数组
        List<ZhangHu> list = zhs.selectType(type, users.getId());
        if (list == null) {
            System.out.println("你这个类型没有支出");
        } else if (list != null) {
            for (ZhangHu hus : list) {
                System.out.println("账户类型：" + hus.getFlname() + "账户余额： " + hus.getMoney() + "账户：" + hus.getZhanghu() + "日期：" + DateUtil.dateFormat(hus.getCreatetime(), "YYYY-MM-DD" + "备注: " + hus.getDescription()));
            }
        }
    }
    private void findByUserid(){

        List<ZhangHu> list1 = zhs.findAccouning(users.getId());

        if (list1 == null){
            System.out.println("该用户没有账户信息");
        }else {
            for (ZhangHu hus : list1) {
                System.out.println("账户："+hus.getZhanghu()+"金额"+hus.getMoney());
            }
        }
    }

    //根据账户查询
    private void findByZhangHu() {
        System.out.println("请输入账户：");
        String account = sc.nextLine();

        List<ZhangHu> list = zhs.findAccouningByZhangHu(account,users.getId());

        for (ZhangHu hus : list) {
            System.out.println("账户类型："+ hus.getFlname()+"账户余额： " +hus.getMoney() + "账户："+hus.getZhanghu()+"日期："+ DateUtil.dateFormat(hus.getCreatetime(),"YYYY-MM-dd"+"备注: "+hus.getDescription()));
        }

    }
    //根据金额范围进行查询
    private void findByDate() {
        System.out.println("请输入用户开始时间：");
        Date begin = DateUtil.dateParse(sc.nextLine());
        System.out.println("请输入用户结束时间： ");
        Date end = DateUtil.dateParse(sc.nextLine());


        String s = zhs.findAccouningByDate(begin,end,users.getId());
        System.out.println(s);
    }

}
