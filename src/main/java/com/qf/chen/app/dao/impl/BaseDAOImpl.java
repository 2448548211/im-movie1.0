package com.qf.chen.app.dao.impl;

import com.qf.chen.app.dao.IBaseDAO;
import com.qf.chen.app.utils.ClassTableName;
import com.qf.chen.app.utils.DButil;
import com.qf.chen.app.utils.FieldColName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ChenWang
 * @className BaseDAOImpl
 * @date 2020/10/15 20:04
 * @since JDK 1.8
 */
public class BaseDAOImpl<T> implements IBaseDAO <T>{
    @Override
    public int insert(String sql, Object... objs) {
        return insert(sql,this.getClass(),objs);
    }
    @Override
    public int insert(String sql, Class<T> tClass, Object... objs) {
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
            t = tClass.newInstance();
            Field[] fields = tClass.getFields();
            String temp;
            if(rSet.next()){
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
    public List<T> selectAll(String id) {
        return null;
    }
}
