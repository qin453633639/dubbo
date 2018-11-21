package com.qhbd.exception;

import com.qhbd.response.StatusResult;

/**
 * @Description:   用户订单自定义异常，方便异常信息统一处理
 * @Author:         qinwei
 * @CreateDate:     2018/10/12 0012 下午 4:29
 * @Version:        1.0
*/
public class KDException  extends  RuntimeException{

    private StatusResult statusResult;

    private KDException() {
        super();
    }

    private KDException(String message) {
        super(message);
    }

    private KDException(String message, Throwable cause) {
        super(message, cause);
    }

    private KDException(Throwable cause) {
        super(cause);
    }

    public KDException(StatusResult statusResult){
        super(statusResult.getMsg());
    }

    private KDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StatusResult getStatusResult() {
        return statusResult;
    }
}
