package com.qhbd.config;

 import com.qhbd.datasource.MultipleDataSource;
 import com.qhbd.datasource.enums.DBType;
 import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:   数据源配置
 * @Author:         qinwei
 * @CreateDate:     2018/9/3 0003 下午 4:02
 * @Version:        1.0
*/
@Configuration
public class DataSourceConfigure {

    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "master")
    @ConfigurationProperties(prefix = "druid.origin.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "druid.origin.slave")
    public DataSource slaveDataSource1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
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
