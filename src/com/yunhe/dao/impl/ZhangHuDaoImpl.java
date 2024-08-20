package com.yunhe.dao.impl;

import com.yunhe.dao.ZhangHuDao;
import com.yunhe.pojo.ZhangHu;
import com.yunhe.util.DateUtil;
import com.yunhe.util.JdbcUtil;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import java.util.Date;
import java.util.List;

public class ZhangHuDaoImpl implements ZhangHuDao {
    JdbcTemplate template = JdbcUtil.template;
    @Override
    public void ZhangHuDao(ZhangHu zh) {
        String sql = "insert into zhanghu values(null,?,?,?,?,?,?)";

        int update = template.update(sql, zh.getFlname(), zh.getMoney(), zh.getZhanghu(), zh.getCreatetime(), zh.getDescription(), zh.getUserid());

    }

    /**
     * 查询支出类型是否正确
     * @param flname
     * @return
     */
    @Override
    public String findByZhangHuType(String flname) {
        String sql = "select zwid from zhanghu where flname = ?";

        List<String> lists = template.queryForList(sql,String.class,flname);
        if (lists.isEmpty()){
            return null;
        }
        return lists.get(0);
    }
    /**
     * 查询信息
     * @param
     */
    @Override
    public List<ZhangHu> fingZhangHu(String flname ,int id) {
        String sql = "select * from zhanghu where flname = ?and userid = ?";
        List<ZhangHu> zhangHus = template.query(sql,new BeanPropertyRowMapper<>(ZhangHu.class),flname,id);
        return zhangHus;
    }
    //、查询当前用户的所有账务信息（日期升序
    @Override
    public List<ZhangHu> findByZhangHuUserid(int userid) {
        String sql = "select * from zhanghu where userid = ? order by createtime";
            List<ZhangHu> list = template.query(sql,new BeanPropertyRowMapper<>(ZhangHu.class),userid);
            return list;

    }
    @Override
    public void fingZhang(int userid) {

        String sql = "select * from zhanghu where userid = ? order by createtime";
        List<ZhangHu> list = template.query(sql, new BeanPropertyRowMapper<>(ZhangHu.class), userid);

        for (ZhangHu hus : list) {
            System.out.println("账户类型："+ hus.getFlname()+"账户余额： " +hus.getMoney() + "账户："+hus.getZhanghu()+"日期："+ DateUtil.dateFormat(hus.getCreatetime(),"YYYY-MM-dd"+"备注: "+hus.getDescription()));
        }
    }
    @Override
    public List<ZhangHu> findZhangHuDate(Date start ,Date last,int userid) {

        String sql = "select * from zhanghu where createtime between ? and ? and userid = ?";
            List<ZhangHu> huList = template.query(sql,new BeanPropertyRowMapper<>(ZhangHu.class),start,last,userid);
        return huList;
    }
    @Override
    public List fingZhangForZhangHu(String account,int userid) {
        String sql = "select * from zhanghu where zhanghu = ? and userid = ?";
        List<ZhangHu> list = template.query(sql,new BeanPropertyRowMapper<>(ZhangHu.class),account,userid);
         return list;
    }

    @Override
    public List<ZhangHu> findByZhangHuMoney(Double minMoney, Double maxMoney, int userid) {
        String sql = "select * from zhanghu where money between ? and ? and userid = ? order by money";
        List<ZhangHu> list = template.query(sql,new BeanPropertyRowMapper<>(ZhangHu.class),minMoney,maxMoney,userid);
        return list;
    }

    //根据账务id 修改当前用户的账务描述
    @Override
    public void updateByDes(String des, int zwid,int userid) {
        String sql = "update zhanghu set description = ? where zwid = ? and userid = ?";
        template.update(sql,des,zwid,userid);
    }
    //根据账务id 修改当前用户的账务金额
    @Override
    public void updateByManey(double money, int zwid, int userid) {
        String sql = "update zhanghu set money = ? where zwid = ? and userid = ?";
        template.update(sql,money,zwid,userid);
    }
    //根据账务id 修改当前用户的账务账户
    @Override
    public void updateByZhangHu(String hu, int zwid, int userid) {
        String sql = "update zhanghu set zhanghu = ? where zwid = ? and userid = ?";
        template.update(sql,hu,zwid,userid);
    }
    //根据账务id 修改当前用户的账务创建时间
    @Override
    public void updateByTime(String time, int zwid, int userid) {
        String sql = "update zhanghu set createtime = ? where zwid = ? and userid = ?";
        template.update(sql,time,zwid,userid);
    }

    //获取用户的所有账户数量
    @Override
    public int findByZhangHu(int userid) {
        String sql = "select count(*) from zhanghu where userid = ?";
        int pageCount = template.queryForObject(sql,new SingleColumnRowMapper<>(int.class),userid);
        return pageCount;

    }
    //查询当前页数
    @Override
    public List<ZhangHu> findByZhangHuLimit(int id, int beginIndex, int pageSize) {
        String sql = "select * from zhanghu where userid = ? limit ?,?";
        return template.query(sql,new BeanPropertyRowMapper<>(ZhangHu.class),id,beginIndex,pageSize);
    }
}
