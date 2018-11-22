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
@ConfigurationProperties(prefix = "dubboConfig.protocolConfig")
public class ProtocolConfigProperties {

    private String name ;
    private Integer port;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
