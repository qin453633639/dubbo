package com.qhbd.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.qhbd.entity.User;
import com.qhbd.mapper.UserMapper;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultVo;
import com.qhbd.service.UserService;
import org.springframework.stereotype.Component;
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

    @Override
    public ResultVo<List<Long>>   findOrderIdsByUserId(CommonParam<Long> entity) {
        this.logger.debug("UserServileImpl.findOrderIdsByUserId param {}", JSON.toJSONString(entity));
        List<Long> list = new ArrayList<>();
        for(long i=0;i<10;i++){
            list.add(i);
        }
        if(list.size() == 10){
            throw new RuntimeException("aaaa");
        }
        this.logger.debug("UserServileImpl.findOrderIdsByUserId response {}", JSON.toJSONString(list));
        return ResultVo.success(list);
    }
}
