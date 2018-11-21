package com.qhbd.datasource.aop;

import com.qhbd.datasource.DataSourceContextHolder;
import com.qhbd.datasource.annotation.Master;
import com.qhbd.datasource.annotation.Slave;
import com.qhbd.datasource.enums.DBType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description:
 *
 *  保证在多切面情况下先切数据源，
 *  如果不先切，若开启事务，则会先创建事务，获取动态的默认数据源中的连接(默认master)，建议数据源切面优先
 * @Author:         qinwei
 * @CreateDate:     2018/9/3 0003 下午 5:05
 * @Version:        1.0
*/

@Aspect
@Component
@Order(0)
public class DataSourceSwitch {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceSwitch.class);

    @Around("@annotation(master)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, Master master) throws Throwable {
        try {
            LOGGER.debug("DataSourceSwitch.proceed set database to master ");
            DataSourceContextHolder.set(DBType.MASTER);
            Object result = proceedingJoinPoint.proceed();
            return result;
        } finally {
            DataSourceContextHolder.clear();
            LOGGER.info("DataSourceSwitch.proceed clear connection");
        }
    }



    @Around("@annotation(slave)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, Slave slave) throws Throwable {
        try {
            LOGGER.info("DataSourceSwitch.proceed  set database to slave ");
            DataSourceContextHolder.set(DBType.SLAVE);
            Object result = proceedingJoinPoint.proceed();
            return result;
        } finally {
            DataSourceContextHolder.clear();
            LOGGER.info("DataSourceSwitch.proceed clear connection");
        }
    }




}
