package com.qhbd.service;

import com.qhbd.entity.User;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultVo;
import java.util.List;

/**
 * @Description:   测试service
 * @Author:         qinwei
 * @CreateDate:     2018/10/12 0012 下午 10:01
 * @Version:        1.0
*/
public interface DemoService  {


    ResultVo<List<Long>> findOrderIdsByUserIdList();

    ResultVo<User> selectByPrimaryKey(CommonParam<User> entity);

    ResultVo<Integer> insert(CommonParam<User> entity);

}
