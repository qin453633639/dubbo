package com.qhbd.service;

import com.qhbd.entity.User;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultVo;
import java.util.List;

public interface UserService  extends  BaseService<User>{

    ResultVo<List<Long>> findOrderIdsByUserId(CommonParam<Long> entity);
}
