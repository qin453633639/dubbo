package com.qhbd.dubbo.loadbalance;

import java.util.List;
import com.alibaba.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:   根据dbid，选择生产者,loadbalance 先于过滤器执行
 * @Author:         qinwei
 * @CreateDate:     2018/10/11 0011 上午 10:53
 * @Version:        1.0
*/

public class SqliteSlotLoadBalance extends AbstractLoadBalance {

	protected final Logger Logger = LoggerFactory.getLogger(SqliteSlotLoadBalance.class);
	public static final String DBID = "DBID";

	@Override
	protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
		try {
			for (Invoker<T> invoker : invokers) {
				return invoker;
			}
		} catch (Exception e) {
			Logger.error("SqliteSlotLoadBalance.doSelect ",e);
		}
		return null;

	}

	@Override
	public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) {
		if (invokers == null || invokers.isEmpty())
			return null;
		return doSelect(invokers, url, invocation);
	}

}
