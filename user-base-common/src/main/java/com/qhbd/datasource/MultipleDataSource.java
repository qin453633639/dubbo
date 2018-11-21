package com.qhbd.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * @Description:   多数据源路由,用于读写分离，默认读主库
 * @Author:         qinwei
 * @CreateDate:     2018/9/3 0003 下午 4:36
 * @Version:        1.0
*/
public class MultipleDataSource extends AbstractRoutingDataSource  {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultipleDataSource.class);

    /***
     * 获取路由key
     * @Author      qinwei
     * @Param       []
     * @Return      java.lang.Object
     * @Exception
     * @Date         2018/9/3 0003 下午 4:23
    */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }


}
