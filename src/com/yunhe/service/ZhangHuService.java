package com.yunhe.service;

import com.yunhe.pojo.ZhangHu;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ZhangHuService {
    String addAccounting(ZhangHu zh);


    List<ZhangHu> selectType(String type,int userid);

    List<ZhangHu> findAccouning(int userid);



    List<ZhangHu> findAccouningByZhangHu(String account ,int userid);

    String findAccouningByDate(Date begin, Date end, int userid);

    List<ZhangHu> findAccouningByMony(Double minMoney, Double maxMoney, int userid);

    void updateZhangHu(String des, int zwid,int userid);

    void updateZhangHuManey(double money, int zwid, int userid);

    void updateHu(String hu, int zwid, int userid);

    void updateTime(String time, int zwid, int userid);

    Map<String, Object> AccouningForLimit(int id, int pageNum);
}
