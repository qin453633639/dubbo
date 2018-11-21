package com.qhbd.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qinwei on 2018/10/23.
 */
@Activate(group = Constants.CONSUMER, order = -1)
public class ConsumerFilter   implements Filter {

    private Logger logger = LoggerFactory.getLogger(ConsumerFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {

            Result result = invoker.invoke(invocation);
            return result;
        } catch (Exception e) {
            // 统一异常处理
            logger.error("ConsumerFilter.invoke", e);
            throw e;
        }

    }

}
