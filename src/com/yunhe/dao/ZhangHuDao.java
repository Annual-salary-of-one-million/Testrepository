package com.yunhe.dao;

import com.yunhe.pojo.ZhangHu;

import java.util.Date;
import java.util.List;

public interface ZhangHuDao {
    void ZhangHuDao(ZhangHu zh);

    String findByZhangHuType(String flname);

    List fingZhangHu(String flname , int id);

    List<ZhangHu> findByZhangHuUserid(int userid);

    void fingZhang(int userid);

    List<ZhangHu> findZhangHuDate(Date createtime, Date createtime1,int userid);

    List fingZhangForZhangHu(String account,int userid);

    List<ZhangHu> findByZhangHuMoney(Double minMoney, Double maxMoney, int userid);

    void updateByDes(String des, int zwid,int userid);

    void updateByManey(double money, int zwid, int userid);

    void updateByZhangHu(String hu, int zwid, int userid);

    void updateByTime(String time, int zwid, int userid);

    int findByZhangHu(int id);

    List<ZhangHu> findByZhangHuLimit(int id, int beginIndex, int pageSize);
}
