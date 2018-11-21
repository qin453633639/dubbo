package com.qhbd.config;

import com.alibaba.dubbo.config.*;
import com.qhbd.response.ResultMsg;
import org.springframework.beans.factory.annotation.Value;
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

    private String mock = "return {" +
            "\"code\":\""+ ResultMsg.DUBBO_CONSUMER_ERROR.getCode()+"\"," +
            "\"msg\":\""+ ResultMsg.DUBBO_CONSUMER_ERROR.getMsg()+"\"" +
            "}";

    //  app 配置
    @Value("${dubboConfig.applicationConfig.appName}")
    private String appName;
    @Value("${dubboConfig.applicationConfig.owner}")
    private String owner;
    @Value("${dubboConfig.applicationConfig.organization}")
    private String organization;

    //  zk 配置
    @Value("${dubboConfig.registryConfig.zk}")
    private String zk;

    //  provider 配置
    @Value("${dubboConfig.providerConfig.server}")
    private String server;
    @Value("${dubboConfig.providerConfig.threads}")
    private Integer threads;
    @Value("${dubboConfig.providerConfig.threadpool}")
    private String threadpool;
    @Value("${dubboConfig.providerConfig.timeout}")
    private Integer timeout;
    @Value("${dubboConfig.providerConfig.providerVersion}")
    private String providerVersion;
    @Value("${dubboConfig.providerConfig.filter}")
    private String filter;
    @Value("${dubboConfig.providerConfig.retries}")
    private Integer retries;

    //  consumer 配置
    @Value("${dubboConfig.consumerConfig.loadbalance}")
    private String loadbalance;
    @Value("${dubboConfig.consumerConfig.cluster}")
    private String cluster;
    @Value("${dubboConfig.consumerConfig.consumerVersion}")
    private String consumerVersion;
    // 服务降级 如果配置后，异常无法捕获
    //@Value("${dubboConfig.consumerConfig.mock}")
    //private String mock;


    //  协议配置
    @Value("${dubboConfig.protocolConfig.protocolName}")
    private String  protocolName;
    @Value("${dubboConfig.protocolConfig.port}")
    private Integer port;

    /**
     * 应用配置
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(appName);
        applicationConfig.setLogger("slf4j");
        applicationConfig.setOwner(owner);
        applicationConfig.setOrganization(organization);
        return applicationConfig;
    }

    /**
     * 注册中心配置
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress(zk);
        registryConfig.setClient("curator");
        return registryConfig;
    }



    /**
     * 为ServiceConfig 提供缺省配置
     * @return
     */
    @Bean
    public ProviderConfig providerConfig(){
        ProviderConfig providerConfig = new ProviderConfig();
        // 使用netty4
        providerConfig.setServer(server);
        // 固定线程数300个 ，快速响应
        providerConfig.setThreads(threads);
        providerConfig.setThreadpool(threadpool);

        // 四秒超时
        providerConfig.setTimeout(timeout);
        // 版本号
        providerConfig.setVersion(providerVersion);

        // 设置dbid到YoushagnContext
        providerConfig.setFilter(filter);

        // 设置重试次数1，不用考虑密等,并且重试此时由服务方控制,消费段不用配置
        providerConfig.setRetries(retries);

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
        consumerConfig.setLoadbalance(loadbalance);
        // 集群扩展
        consumerConfig.setCluster(cluster);

        // 版本号
        consumerConfig.setVersion(consumerVersion);

        // 服务降级，配置后会导致非业务逻辑异常吞掉，调用者自己扩展
         //consumerConfig.setMock(mock);
        return consumerConfig;
    }

    /**
     * 协议配置
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName(protocolName);
        protocol.setPort(port);
        return protocol;
    }

}
