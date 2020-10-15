package com.qf.chen.app.dao;

import java.util.List;

/**
 * @author ChenWang
 * @interfaceName BaseDAO
 * @date 2020/10/15 20:04
 * @since JDK 1.8
 */
public interface IBaseDAO <T>{
    int insert(String sql,Class<T> tClass,Object...objs);
    int insert(String sql,Object...objs);
    T selectById(String sql,Class<T> tClass,String id,Object...objs);
    List<T> selectAll(String id);
}
