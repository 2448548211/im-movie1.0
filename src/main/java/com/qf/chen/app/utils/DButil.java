package com.qf.chen.app.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ChenWang
 * @className DButil
 * @date 2020/10/15 19:31
 * @since JDK 1.8
 */
public class DButil {
    private static String CLASSNAME = null;
    private static String URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;
    static{
        try {
            Properties properties = new Properties();
            //通过类加载器的相对路径加载对应的配置文件
            properties.load(DButil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            CLASSNAME = properties.getProperty("classname");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取指定数据库连接
     * @return  数据库连接Connection
     * @author ChenWang
     * @date 2020/10/15 19:51
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(CLASSNAME);
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    /**
     * 释放资源
     * @param closeables    指定的需要关闭的资源
     * @author ChenWang
     * @date 2020/10/15 19:53
     */
    public static void closeAll(AutoCloseable...closeables){
        for (AutoCloseable closeable:closeables){
            if(closeable!=null){
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
