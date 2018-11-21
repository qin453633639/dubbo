package com.qhbd;

import com.alibaba.fastjson.JSON;
import com.qhbd.entity.User;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultVo;
import com.qhbd.service.DemoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @Description:   测试rpc demo
 * @Author:         qinwei
 * @CreateDate:     2018/10/12 0012 下午 10:04
 * @Version:        1.0
*/
public class DemoServiceTest extends  BaseTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void testFindOrderIdsByUserIdList(){
        ResultVo<List<Long>> data = demoService.findOrderIdsByUserIdList();
        System.out.println(JSON.toJSON(data).toString());
    }

    @Test
    public void testSelectByPrimaryKey(){
        User u = new User();
        u.setUserId(41);
        ResultVo<User> data = demoService.selectByPrimaryKey( new CommonParam<User>(u));
        System.out.println(data);
    }

    @Test
    public void testInsert(){
        User u = new User();
        u.setUserName("conmsumer");
        ResultVo<Integer> data = demoService.insert( new CommonParam<User>(u));
        System.out.println(data);
    }



}
