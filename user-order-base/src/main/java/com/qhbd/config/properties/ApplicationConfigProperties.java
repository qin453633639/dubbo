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
@ConfigurationProperties(prefix = "dubboconfig.applicationconfig")
public class ApplicationConfigProperties {

    private String name;
    private String logger;
    private String owner;
    private String organization;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
