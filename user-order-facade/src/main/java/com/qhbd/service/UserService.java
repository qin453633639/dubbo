package com.qhbd.service;

import com.qhbd.entity.User;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultVo;
import java.util.List;

/**
 * @Description:   demo
 * @Author:         qinwei
 * @CreateDate:     2018/11/22 0022 上午 11:34
 * @Version:        1.0
*/
public interface UserService  extends  BaseService<User>{

    ResultVo<List<Long>> findOrderIdsByUserId(CommonParam<Long> entity);
}
