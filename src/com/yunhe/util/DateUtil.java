package com.yunhe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String dateFormat(Date date ,String patter){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patter);

        String format = simpleDateFormat.format(date);
        return format;
    }
    public static Date dateParse(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-MM-dd");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String dateFormat(Date createtime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-MM-dd");
        String format = simpleDateFormat.format(createtime);
        return format;
    }
}
