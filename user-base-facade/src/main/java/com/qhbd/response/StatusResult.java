package com.qhbd.response;

public interface StatusResult {
    /**
     * 返回错误状态
     *
     * @return
     */
    int getCode();


    /**
     * 返回错误信息
     *
     * @return
     */
    String getMsg();

    /** jdk 版本原因 暂时去掉，
    default <T> ResultVo<T> info() {
        return new ResultVo<>(this);
    }


    default <T> ResultVo<T> info(String msg) {
        return new ResultVo<>(this, msg);
    }

    default <T> ResultVo<T> info(Exception exception) {
        return new ResultVo<>(this, exception.getMessage(),  null);
    }


    default <T> ResultVo<T> info(String msg, T data) {
        return new ResultVo<>(this, msg, data);
    }


    default <T> ResultVo<T> info(T data) {
        return new ResultVo<>(this, data);
    }
    */
}
