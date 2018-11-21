package com.qhbd.controller;

import com.qhbd.entity.User;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultVo;
import com.qhbd.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @Description:   测试controller 消费
 * @Author:         qinwei
 * @CreateDate:     2018/10/12 0012 下午 10:01
 * @Version:        1.0
*/
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/findOrderIdsByUserIdList")
    @ResponseBody
    public ResultVo<List<Long>> findOrderIdsByUserIdList(){

        return demoService.findOrderIdsByUserIdList();
    }

    @RequestMapping("/selectByPrimaryKey")
    @ResponseBody
    public ResultVo<User> selectByPrimaryKey(CommonParam<User> entity){
        User u = new User();
        u.setUserId(41);
        ResultVo<User> data = demoService.selectByPrimaryKey( new CommonParam<User>(u));
        return data;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public ResultVo<Integer> insert(CommonParam<User> entity){
        User u = new User();
        u.setUserName("conmsumer");
        ResultVo<Integer> data = demoService.insert( new CommonParam<User>(u));
        return data;
    }
}
