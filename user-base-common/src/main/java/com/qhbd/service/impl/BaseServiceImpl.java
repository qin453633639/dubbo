package com.qhbd.service.impl;

import com.qhbd.datasource.annotation.Master;
import com.qhbd.datasource.annotation.Slave;
import com.qhbd.mapper.BaseMapper;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultVo;
import com.qhbd.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:   通用service实现
 * @Author:         qinwei
 * @CreateDate:     2018/9/5 0005 上午 10:40
 * @Version:        1.0
*/
public  class BaseServiceImpl<M extends BaseMapper<T>,T> implements BaseService<T> {

    @Autowired
    protected M mapper;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    @Master
    public ResultVo<Integer> insert(CommonParam<T> entity) {
        return ResultVo.success(mapper.insert(entity.getData())) ;
    }


    @Transactional
    @Master
    public ResultVo<Integer> insertSelective(CommonParam<T> entity) {
        return ResultVo.success(mapper.insertSelective(entity.getData()));
    }


    @Transactional
    @Master
    public ResultVo<Integer> updateByPrimaryKey(CommonParam<T> entity) {
        return ResultVo.success(mapper.updateByPrimaryKey(entity.getData()));
    }


    @Transactional
    @Master
    public ResultVo<Integer> updateByPrimaryKeySelective(CommonParam<T> entity) {
        return ResultVo.success(mapper.updateByPrimaryKeySelective(entity.getData()));
    }

    @Transactional
    @Master
    public ResultVo<Integer> deleteByPrimaryKey(CommonParam<T> entity) {
        return ResultVo.success(mapper.deleteByPrimaryKey(entity.getData()));
    }


    @Override
    @Slave
    public ResultVo<T> selectByPrimaryKey(CommonParam<T> entity) {
        return ResultVo.success(mapper.selectByPrimaryKey(entity.getData()));
    }
}