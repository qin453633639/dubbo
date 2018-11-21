package com.qhbd.mapper;

/**
 * @Description:   通用mapper
 * @Author:         qinwei
 * @CreateDate:     2018/9/5 0005 上午 12:17
 * @Version:        1.0
*/
public interface BaseMapper<T> {

    int insert(T entity);

    int insertSelective(T entity);

    int updateByPrimaryKey(T entity);

    int updateByPrimaryKeySelective(T entity);

    int deleteByPrimaryKey(T primary);

    T selectByPrimaryKey(T id);

}
