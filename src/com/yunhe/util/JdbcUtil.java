package com.yunhe.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUtil {
    public static JdbcTemplate template;

    static {

        template = new JdbcTemplate(new ComboPooledDataSource());

    }

}
