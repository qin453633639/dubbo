package com.qhbd.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:   配置属性类
 * @Author:         qinwei
 * @CreateDate:     2018/11/22 0022 上午 11:45
 * @Version:        1.0
 */

@Component
@ConfigurationProperties(prefix = "dubboconfig.consumerconfig")
public class ConsumerConfigProperties {

    private String loadbalance;

    private String cluster;

    private String version;

    public String getLoadbalance() {
        return loadbalance;
    }

    public void setLoadbalance(String loadbalance) {
        this.loadbalance = loadbalance;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
