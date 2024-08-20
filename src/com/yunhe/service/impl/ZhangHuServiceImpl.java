package com.yunhe.service.impl;

import com.yunhe.dao.ZhangHuDao;
import com.yunhe.dao.impl.ZhangHuDaoImpl;
import com.yunhe.pojo.ZhangHu;
import com.yunhe.service.ZhangHuService;
import com.yunhe.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhangHuServiceImpl implements ZhangHuService {

    ZhangHuDao zhd = new ZhangHuDaoImpl();
    @Override
    public String addAccounting(ZhangHu zh) {
        zhd.ZhangHuDao(zh);

        return "添加成功";
    }
    /**
     * 根据支出类型查询
     *
     */
    @Override
    public List<ZhangHu> selectType(String type, int userid) {
        String type1 =  zhd.findByZhangHuType(type);

        List<ZhangHu> list = zhd.fingZhangHu(type,userid);
        return list;
    }

    @Override
    public List<ZhangHu> findAccouning(int userid) {
        List<ZhangHu> list = zhd.findByZhangHuUserid(userid);
       return list;
    }

    @Override
    public String findAccouningByDate(Date begin, Date end, int userid) {

        List<ZhangHu> zhangHus =  zhd.findZhangHuDate(begin,end,userid);
        if (zhangHus.size() == 0){
            return "失败";
        }
        for (ZhangHu hus : zhangHus) {

            System.out.println("账户类型："+ hus.getFlname()+"账户余额： " +hus.getMoney() + "账户："+hus.getZhanghu()+"日期："+ DateUtil.dateFormat(hus.getCreatetime(),"YYYY-MM-dd"+"备注: "+hus.getDescription()));
        }

        return "成功";
    }

    //最少金额和最大金额
    @Override
    public List<ZhangHu> findAccouningByMony(Double minMoney, Double maxMoney, int userid) {
        List<ZhangHu> list = zhd.findByZhangHuMoney(minMoney, maxMoney,userid);
        return list;
    }

    //根据id修改备注
    @Override
    public void updateZhangHu(String des, int zwid,int userid) {
        zhd.updateByDes(des,zwid,userid);
    }
//修改金额
    @Override
    public void updateZhangHuManey(double money, int zwid, int userid) {
        zhd.updateByManey(money,zwid,userid);
    }
//修改账户
    @Override
    public void updateHu(String hu, int zwid, int userid) {
        zhd.updateByZhangHu(hu,zwid,userid);
    }
//修改时间
    @Override
    public void updateTime(String time, int zwid, int userid) {
        zhd.updateByTime(time,zwid,userid);
    }

    //分页查询
    @Override
    public Map<String, Object> AccouningForLimit(int id, int pageNum) {
       //定义每页的长度
        int pageSize = 2;
        //定义页码总数：账务总数/pageSize,如果有余数或者为0，总数加一；
        //查询用户业务总数
        int count = zhd.findByZhangHu(id);
        //查询页码总数
        int pageCount = count/pageSize;
        if (count == 0 || (count%pageSize) != 0){
            pageCount++;
        }
        if (pageNum<0){
            pageNum = 1;
        }else if (pageNum>pageCount){
            pageNum = pageCount;
        }
        //计算起始页的下标
        int beginIndex = (pageNum-1)*pageSize;

        List<ZhangHu> list = zhd.findByZhangHuLimit(id,beginIndex,pageSize);

        HashMap<String,Object> maps = new HashMap<>();
        maps.put("zhanghuList",list);
        maps.put("pageNum",pageNum);
        maps.put("pageCount",pageCount);
        return maps;
    }

    @Override
    public List<ZhangHu> findAccouningByZhangHu(String account,int userid) {
       List<ZhangHu> lists = zhd.fingZhangForZhangHu(account,userid);

        for (ZhangHu hus : lists) {
            System.out.println("账户类型："+ hus.getFlname()+"账户余额： " +hus.getMoney() + "账户："+hus.getZhanghu()+"日期："+ DateUtil.dateFormat(hus.getCreatetime(),"YYYY-MM-dd"+"备注: "+hus.getDescription()));
        }
        return lists;
    }

}
