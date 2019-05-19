package com.qhbd.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.qhbd.datasource.annotation.Slave;
import com.qhbd.entity.User;
import com.qhbd.mapper.UserMapper;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultVo;
import com.qhbd.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheResult;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:   demo
 * @Author:         qinwei
 * @CreateDate:     2018/9/3 0003 下午 3:22
 * @Version:        1.0
*/

@Component("userService")
@Service
public class UserServileImpl extends  BaseServiceImpl<UserMapper, User>  implements UserService {


    public UserServileImpl() {
    }

    @Override
    public ResultVo<List<Long>>   findOrderIdsByUserId(CommonParam<Long> entity) {
        this.logger.debug("UserServileImpl.findOrderIdsByUserId param {}", JSON.toJSONString(entity));
        List<Long> list = new ArrayList<>();
        for(long i=0;i<10;i++){
            list.add(i);
        }
        this.logger.debug("UserServileImpl.findOrderIdsByUserId response {}", JSON.toJSONString(list));
        return ResultVo.success(list);
    }

    @Override
    //@CacheResult(cacheName = "default")
    @Cacheable(value = "default")
    @Slave
    public ResultVo<User> findByUserId(CommonParam<Long> id){
        this.logger.debug("UserServileImpl.findByUserId param {}", JSON.toJSONString(id));
        User u = new User();
        u.setUserId(id.getData().intValue());
        return ResultVo.success(this.mapper.selectByPrimaryKey(u)) ;

    }

    @Override
    @Cacheable(value = "100" )
    @Slave
    public ResultVo<User> findByUserId(String id) {
        this.logger.debug("UserServileImpl.findByUserId param {}", JSON.toJSONString(id));
        User u = new User();
        u.setUserId(Integer.parseInt(id));
        return ResultVo.success(this.mapper.selectByPrimaryKey(u)) ;
    }
}
