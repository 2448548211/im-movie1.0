package com.qf.chen.app.dao.impl;

import com.qf.chen.app.dao.IBaseDAO;
import com.qf.chen.app.utils.ClassTableName;
import com.qf.chen.app.utils.DButil;
import com.qf.chen.app.utils.FieldColName;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

/**
 * @author ChenWang
 * @className BaseDAOImpl
 * @date 2020/10/15 20:04
 * @since JDK 1.8
 */
public class BaseDAOImpl<T> implements IBaseDAO <T>{
    private static final String SELECT_ALL = "SELECT";
    private static final String SPACE = " ";
    private static final String FROM = "FROM";

    @Override
    public int update(String sql, Class<T> tClass, Object... objs) {
        int affectedRows = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DButil.getConnection();
            ps = conn.prepareStatement(sql);
            ClassTableName annotation = tClass.getAnnotation(ClassTableName.class);
            ps.setObject(1,annotation.value());
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i+2,objs[i]);
            }
            affectedRows = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DButil.closeAll(ps,conn);
        }
        return affectedRows;
    }
    @Override
    /**
     * 依据ID获取对应的数据库的数据
     * @param sql   select查询语句
	 * @param id    指定的id
	 * @param objs  查询的字段名
     * @return      返回第一个查询到的数据
     * @author ChenWang
     * @date 2020/10/15 20:47
     */
    public T selectById(String sql,Class<T> tClass,String id,Object...objs) {
        T t = null;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DButil.getConnection();
            ps = conn.prepareStatement(sql);
            ClassTableName annotation = this.getClass().getAnnotation(ClassTableName.class);
            ps.setObject(1,annotation.value());
            int i=2;
            for (; i < objs.length; i++) {
                ps.setObject(i,objs[i]);
            }
            ps.setObject(i,id);
            ResultSet rSet = ps.executeQuery();
            Field[] fields = tClass.getFields();
            String temp;
            if(rSet.next()){
                t = tClass.newInstance();
                for (Field f : fields) {
                    temp = f.getAnnotation(FieldColName.class).value();
                    temp = (temp!=null&&temp!="")?temp:f.getName();
                    f.set(t,rSet.getObject(temp));
                }
            }
        } catch (SQLException | InstantiationException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }finally {
            DButil.closeAll(ps,conn);
        }
        return t;
    }

    @Override
    public List<T> selectAll(Class<T> tClass) {
        StringBuffer sql = new StringBuffer(SELECT_ALL);
        List<T> list = null;
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DButil.getConnection();
            Field[] fields = tClass.getFields();
            T t = null;
            String colName = null;
            FieldColName annotation = null;
            for (Field f:fields){
                annotation = f.getAnnotation(FieldColName.class);
                colName = (annotation==null||"".equals(annotation.value())) ? f.getName():annotation.value();
                sql.append(SPACE);
                sql.append(colName);
            }
            sql.append(SPACE);
            sql.append(FROM);
            /*
            传入参数不是直接的sql语句字符串，风险较小，所以直接添加到sql语句后了
            将传入的实体类对应的表添加到sql语句后面
            */
            sql.append(tClass.getAnnotation(ClassTableName.class).value());
            Statement st = conn.createStatement();
            ResultSet rSet = st.executeQuery(sql.toString());
            while(rSet.next()){
                t = tClass.newInstance();
                for (Field f:fields){
                    annotation = f.getAnnotation(FieldColName.class);
                    colName = (annotation==null||"".equals(annotation.value())) ? f.getName():annotation.value();
                    f.set(t,rSet.getObject(colName));
                }
            }
        } catch (SQLException | InstantiationException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
