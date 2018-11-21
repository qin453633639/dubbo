package com.qhbd.service;

import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultVo;

/**
 * @Description:   通用service接口
 * @Author:         qinwei
 * @CreateDate:     2018/9/5 0005 上午 10:40
 * @Version:        1.0
*/
public interface BaseService<T> {


    ResultVo<Integer> insert(CommonParam<T> entity);


    ResultVo<Integer> insertSelective(CommonParam<T> entity);


    ResultVo<Integer> updateByPrimaryKey(CommonParam<T> entity);


    ResultVo<Integer> updateByPrimaryKeySelective(CommonParam<T> entity);


    ResultVo<Integer> deleteByPrimaryKey(CommonParam<T> entity);

    ResultVo<T> selectByPrimaryKey(CommonParam<T> entity);
}
