package com.qhbd;

import com.qhbd.entity.User;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultMsg;
import com.qhbd.response.ResultVo;
import com.qhbd.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qinwei on 2018/9/23.
 */
public class UserServiceTest extends  BaseTest {

     @Autowired
    private UserService userService;

    @Test
    public void testSelectByPrimaryKey(){
        User u = new User();
        u.setUserId(31);
        ResultVo<User> data = userService.selectByPrimaryKey(new CommonParam<User>(u));
        System.out.println(data.getData());

    }

    @Test
    public void testInsert(){
        User u = new User();
        //u.setUserId(40);
        u.setUserName("qinwei12321");
        ResultVo<Integer> data = userService.insert(new CommonParam<User>(u));
        System.out.println(data.getData());

    }




}
