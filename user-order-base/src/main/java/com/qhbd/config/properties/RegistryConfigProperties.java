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
@ConfigurationProperties(prefix = "dubboConfig.registryConfig")
public class RegistryConfigProperties {
    private String protocol;

    private String address;
    private String client;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
