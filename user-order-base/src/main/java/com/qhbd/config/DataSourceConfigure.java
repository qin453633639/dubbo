package com.qhbd.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.qhbd.datasource.MultipleDataSource;
import com.qhbd.datasource.enums.DBType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 数据源配置
 * @Author: qinwei
 * @CreateDate: 2018/9/3 0003 下午 4:02
 * @Version: 1.0
 */
@Configuration
public class DataSourceConfigure {

    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "master")
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://119.23.36.49:3306/test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true");
        dataSource.setUsername("root");
        dataSource.setPassword("qinwei");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(10);

        try {
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;

    }

    @Bean(name = "slave")
    public DataSource slaveDataSource1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://119.23.36.49:3306/test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true");
        dataSource.setUsername("root");
        dataSource.setPassword("qinwei");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(10);
        try {
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    @Bean(name = "dataSource")
    @Primary
    public AbstractRoutingDataSource dataSource() {
        MultipleDataSource proxy = new MultipleDataSource();
        Map<Object, Object> targetDataResources = new HashMap<>();
        targetDataResources.put(DBType.MASTER, masterDataSource());
        targetDataResources.put(DBType.SLAVE, slaveDataSource1());
        proxy.setDefaultTargetDataSource(masterDataSource());
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }
}
