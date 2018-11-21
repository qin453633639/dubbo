package com.qhbd.param;

import java.io.Serializable;

/**
 * @Description:   通用形参，保证接口参数统一
 * @Author:         qinwei
 * @CreateDate:     2018/10/12 0012 下午 3:51
 * @Version:        1.0
*/
public class CommonParam<T> implements Serializable {

    private static final long serialVersionUID = 6095755606671547259L;


    private Long dbid;


    private T data;

    public Long getDbid() {
        return dbid;
    }

    public void setDbid(Long dbid) {
        this.dbid = dbid;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CommonParam(Long dbid, T data) {
        this.dbid = dbid;
        this.data = data;
    }
    public CommonParam( ) {

    }

    public CommonParam( T  t) {
        this.data= t;
    }
}
