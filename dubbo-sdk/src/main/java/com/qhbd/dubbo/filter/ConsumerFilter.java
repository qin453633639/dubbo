package com.qhbd.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.qhbd.response.ResultMsg;
import com.qhbd.response.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:   处理dubbo调用异常，如超时异常等等，
 * @Author:         qinwei
 * @CreateDate:     2018/11/22 0022 下午 2:44
 * @Version:        1.0
*/

@Activate(group = Constants.CONSUMER, order = -1)
public class ConsumerFilter   implements Filter {

    private Logger logger = LoggerFactory.getLogger(ConsumerFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            Result result = invoker.invoke(invocation);
            return result;
        } catch (Throwable e) {
            logger.error("ConsumerFilter.invoke", e);
            return new  RpcResult( new ResultVo(ResultMsg.DUBBO_CONSUMER_ERROR,e));
        }
    }

}
