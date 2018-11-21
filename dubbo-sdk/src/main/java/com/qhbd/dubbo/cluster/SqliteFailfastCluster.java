package com.qhbd.dubbo.cluster;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Cluster;
import com.alibaba.dubbo.rpc.cluster.Directory;

/**
 * @Description:   针对sqlite槽选主机时的bug，如果提供这只有一个，
 *                    默认的逻辑不会去根据dbid选在invoker
 * @Author:         qinwei
 * @CreateDate:     2018/10/11 0011 下午 6:58
 * @Version:        1.0
*/
public class SqliteFailfastCluster  implements Cluster {

    public final static String NAME = "sqliteFailfastCluster";

    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        return new SqliteFailfastClusterInvoker<T>(directory);
    }
}
