package com.qhbd.config;

import com.alibaba.dubbo.config.*;
import com.qhbd.config.properties.*;
import com.qhbd.response.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: dubbo配置
 * @Author: qinwei
 * @CreateDate: 2018/9/3 0003 下午 3:22
 * @Version: 1.0
 */
@Configuration
public class DubboConfiguration {

    /**
     * 服务降级，duboo自带的有点蠢，后面集成hytrix做降级
     */
    private String mock = "return {" +
            "\"code\":\""+ ResultMsg.DUBBO_CONSUMER_ERROR.getCode()+"\"," +
            "\"msg\":\""+ ResultMsg.DUBBO_CONSUMER_ERROR.getMsg()+"\"" +
            "}";

    @Autowired
    private ApplicationConfigProperties applicationConfigProperties;

    @Autowired
    private RegistryConfigProperties registryConfigProperties;

    @Autowired
    private ProviderConfigProperties providerConfigProperties;

    @Autowired
    private ConsumerConfigProperties configurationProperties;

    @Autowired
    private ProtocolConfigProperties protocolConfigProperties;


    /**
     * 应用配置
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationConfigProperties.getName());
        applicationConfig.setLogger(applicationConfigProperties.getLogger());
        applicationConfig.setOwner(applicationConfigProperties.getOwner());
        applicationConfig.setOrganization(applicationConfigProperties.getOrganization());
        return applicationConfig;
    }

    /**
     * 注册中心配置
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol(registryConfigProperties.getProtocol());
        registryConfig.setAddress(registryConfigProperties.getAddress());
        registryConfig.setClient(registryConfigProperties.getClient());
        return registryConfig;
    }


    /**
     * 为ServiceConfig 提供缺省配置
     * @return
     */
    @Bean
    public ProviderConfig providerConfig(){
        ProviderConfig providerConfig = new ProviderConfig();

       // providerConfig.setServer(server);
        // 固定线程数300个 ，快速响应
        providerConfig.setThreads(providerConfigProperties.getThreads());
        providerConfig.setThreadpool(providerConfigProperties.getThreadpool());

        // 四秒超时
        providerConfig.setTimeout(providerConfigProperties.getTimeout());
        // 版本号
        providerConfig.setVersion(providerConfigProperties.getVersion());

        // 设置dbid到YoushagnContext
        if(providerConfigProperties.getFilter() != null){
            providerConfig.setFilter(providerConfigProperties.getFilter());
        }
        // 设置重试次数1，不用考虑密等,并且重试此时由服务方控制,消费段不用配置
        providerConfig.setRetries(providerConfigProperties.getRetries());

        return providerConfig;
    }

    /**
     * 消费者配置
     * @return
     */
    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        // 负载均衡扩展
        consumerConfig.setLoadbalance(configurationProperties.getLoadbalance());
        // 集群扩展
        consumerConfig.setCluster(configurationProperties.getCluster());

        // 版本号
        consumerConfig.setVersion(configurationProperties.getVersion());

        // 服务降级，配置后会导致非业务逻辑异常吞掉，调用者自己扩展
        // consumerConfig.setMock(mock);
        return consumerConfig;
    }

    /**
     * 协议配置
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName(protocolConfigProperties.getName());
        protocol.setPort(protocolConfigProperties.getPort());
        return protocol;
    }

}
