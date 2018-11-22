package com.qhbd.config;

import com.alibaba.dubbo.config.*;
import com.qhbd.response.ResultMsg;
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

    /**
     * 应用配置
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("user-order-consumer");
        applicationConfig.setLogger("slf4j");
        applicationConfig.setOwner("user-order-base");
        applicationConfig.setOrganization("qhbd");
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
        registryConfig.setAddress("119.23.36.49:2181");
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

        // 固定线程数300个 ，快速响应
        providerConfig.setThreads(200);
        providerConfig.setThreadpool("fixed");

        // 四秒超时
        providerConfig.setTimeout(4000);
        // 版本号
        providerConfig.setVersion("1.0");

        // 设置dbid到YoushagnContext
        providerConfig.setFilter("exceptionFilter,providerFilter");

        // 设置重试次数1，不用考虑密等,并且重试此时由服务方控制,消费段不用配置
        providerConfig.setRetries(0);

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
        consumerConfig.setLoadbalance("sqliteslot");
        // 集群扩展
        consumerConfig.setCluster("sqliteFailfastCluster");

        // 版本号
        consumerConfig.setVersion("1.0");
        consumerConfig.setFilter("consumerFilter");
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
        protocol.setName("dubbo");
        protocol.setPort(8091);
        return protocol;
    }

}
