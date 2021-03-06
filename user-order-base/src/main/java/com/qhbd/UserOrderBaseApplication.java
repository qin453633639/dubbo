package com.qhbd;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@MapperScan("com.qhbd.mapper")
@DubboComponentScan(basePackages = "com.qhbd")
public class UserOrderBaseApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserOrderBaseApplication.class);
    private static final Object lock = new Object();
    public static void main( String[] args ) {
        synchronized (lock){
            LOGGER.debug("SSOJobApplication.main {}","正在启动 UserOrderBaseApplication......");
            ConfigurableApplicationContext context = SpringApplication.run(UserOrderBaseApplication.class, args);
            context.registerShutdownHook();
            LOGGER.debug("SSOJobApplication.main {}","UserOrderBaseApplication...... 启动成功");
            try {
                lock.wait();
            } catch (InterruptedException e) {

            }
        }
    }
}
