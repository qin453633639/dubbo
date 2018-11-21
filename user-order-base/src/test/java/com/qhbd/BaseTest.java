package com.qhbd;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:   测试基类
 * @Author:         qinwei
 * @CreateDate:     2018/10/12 0012 下午 9:51
 * @Version:        1.0
*/

@RunWith(SpringRunner.class)
@SpringBootTest(classes={UserOrderBaseApplication.class})// 指定启动类
public class BaseTest {
}
