package com.qhbd.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.qhbd.param.CommonParam;
import com.qhbd.response.ResultMsg;
import com.qhbd.response.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将evn信息设置到本地环境中
 */
@Activate(group = Constants.PROVIDER, order = -1)
public class DBIDFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(DBIDFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            RpcInvocation rpc = (RpcInvocation) invocation;
            Object[] params =  invocation.getArguments();
            if(params == null || params.length != 1 ||  !(params[0] instanceof CommonParam)){
                return  new   RpcResult( new ResultVo(ResultMsg.PARAM_ERROR));
            }
            Long dbid = ((CommonParam)params[0]).getDbid();
            if(dbid == null){
                return  new   RpcResult( new ResultVo(ResultMsg.DBID_NULL));
            }
            Result result = invoker.invoke(invocation);
            return result;
        } catch (Exception e) {
            // 统一异常处理
            logger.error("DBIDFilter.invoke", e);
            throw e;
        }

    }



}
