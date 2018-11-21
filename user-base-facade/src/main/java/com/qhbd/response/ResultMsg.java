package com.qhbd.response;

/**
 * 错误信息枚举
 */
public enum  ResultMsg implements StatusResult {

    DUBBO_CONSUMER_ERROR(-2,"DBUUO 调用异常"),
    FAIL(-1,"服务端错误"),
    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数错误"),
    DBID_NULL(2,"DBID为空"),

    /**
     * 10000 - 20000 用户订单自定义错误码
     */
    USER_NOT_EXIT(10000,"用户不存在")

    ;

    /**
     * 状态
     */
    private final int code;
    /**
     * 状态描述
     */
    private String msg;

    ResultMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }


    public int getCode() {
        return this.code;
    }


    public String getMsg() {
        return this.msg;
    }
}
