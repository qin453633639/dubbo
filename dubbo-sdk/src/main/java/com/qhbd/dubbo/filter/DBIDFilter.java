package com.qhbd.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
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
            String dbid = rpc.getAttachment("DBID");
            /*if(StringUtils.isBlank(dbid)){
                return  new   RpcResult( new ResultVo(ResultMsg.DBID_NULL));
            }*/
          /*  YoushangContext context = new YoushangContext(0);
            context.setDatabaseId(Long.parseLong(dbid));
            Env.setContext(context);*/
            logger.debug("DBIDFilter.invoke set dbid {}", dbid);
            Result result = invoker.invoke(invocation);
            return result;
        } catch (Exception e) {
            // 统一异常处理
            logger.error("DBIDFilter.invoke", e);
            throw e;
        }

    }



}
