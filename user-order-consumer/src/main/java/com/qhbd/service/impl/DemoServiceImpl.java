package com.qhbd.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qhbd.entity.User;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultVo;
import com.qhbd.service.DemoService;
import com.qhbd.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description:   测试实现
 * @Author:         qinwei
 * @CreateDate:     2018/10/12 0012 下午 10:02
 * @Version:        1.0
*/

@Service
public class DemoServiceImpl implements DemoService {

    @Reference
    private UserService userService;

    @Override
    public ResultVo<List<Long>> findOrderIdsByUserIdList() {
        return  userService.findOrderIdsByUserId(new CommonParam<Long>());

    }

    @Override
    public ResultVo<User> selectByPrimaryKey(CommonParam<User> entity) {
        return userService.selectByPrimaryKey(entity);
    }

    @Override
    public ResultVo<Integer> insert(CommonParam<User> entity) {
        return userService.insert(entity);
    }
}
