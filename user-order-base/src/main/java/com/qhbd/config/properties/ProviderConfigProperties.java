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
@ConfigurationProperties(prefix = "dubboconfig.providerconfig")
public class ProviderConfigProperties {

    private Integer threads;
    private String threadpool;
    private Integer timeout;
    private String version;

    private String filter;

    private Integer retries;

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public String getThreadpool() {
        return threadpool;
    }

    public void setThreadpool(String threadpool) {
        this.threadpool = threadpool;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }
}
