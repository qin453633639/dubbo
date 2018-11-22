package com.qhbd.response;

import java.io.Serializable;

/**
 * @Description:   通用返回dto
 * @Author:         qinwei
 * @CreateDate:     2018/9/3 0003 下午 11:12
 * @Version:        1.0
*/
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 6095755606671547258L;

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态描述
     */
    private String msg;

    /**
     * 详细编码
     */
    private int detailCode;


    /**
     * 返回数据
     */
    private T data;


    public ResultVo() {
    }

    public ResultVo(StatusResult statusResult){
        this(statusResult, null, null);
    }

    public ResultVo(StatusResult statusResult, String msg){
        this(statusResult, msg, null);
    }

    public ResultVo(StatusResult statusResult, T data){
        this(statusResult, null, data);
    }

    public ResultVo(StatusResult statusResult, String msg,  T data){
        this.code= statusResult.getCode();
        this.msg=  msg == null? statusResult.getMsg()  : msg;
        this.data=data;
    }


    public static <T> ResultVo<T> success(){
        return success(null);
    }
    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<T>(ResultMsg.SUCCESS).data(data);
    }


    public ResultVo<T> msg(String msg){
        this.msg = msg;
        return this;
    }


    public ResultVo<T> data(T data){
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    /**
     * 设置状态码
     * @param code 值必须满足 {@link ResultMsg#code}
     */
    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public ResultVo<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(int detailCode) {
        this.detailCode = detailCode;
    }

    @Override
    public String toString() {
        return "ResultVo [code=" + code + ", msg=" + msg + ", detailCode=" + detailCode + ", data=" + data + "]";
    }
}
