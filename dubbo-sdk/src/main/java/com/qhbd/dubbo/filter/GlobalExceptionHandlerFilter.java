package com.qhbd.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.qhbd.exception.KDException;
import com.qhbd.response.ResultMsg;
import com.qhbd.response.ResultVo;
import com.qhbd.response.StatusResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:   全局统异常处理
 * @Author:         qinwei
 * @CreateDate:     2018/10/12 0012 下午 6:41
 * @Version:        1.0
*/
@Activate(group = Constants.PROVIDER, order = -2)
public class GlobalExceptionHandlerFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            Result result = invoker.invoke(invocation);
            if (  ! result.hasException() || ! (GenericService.class != invoker.getInterface()) ) {
                return result;
            }
            ResultVo  resultVo =  null;
            Throwable exception = result.getException();
            if(exception instanceof KDException){
                // 手动抛出的异常
                StatusResult statusResult   =  ((KDException) exception).getStatusResult();
                resultVo = new ResultVo(statusResult);
            }else{
                // 未知异常，直接提示服务器错误
                resultVo = new ResultVo(ResultMsg.FAIL);
            }
            logger.error("GlobalExceptionHandlerFilter.invoke", exception);
            return new  RpcResult(resultVo);
        }catch (KDException e){
            logger.error("GlobalExceptionHandlerFilter.invoke", e);
            return new  RpcResult( new ResultVo(  e.getStatusResult() ));
        } catch (Throwable e) {
            logger.error("GlobalExceptionHandlerFilter.invoke", e);
            return new  RpcResult( new ResultVo(ResultMsg.FAIL));
        }

    }
}
