package com.qhbd.datasource;

import com.qhbd.datasource.enums.DBType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:   数据源持有
 * @Author:         qinwei
 * @CreateDate:     2018/9/3 0003 下午 4:16
 * @Version:        1.0
*/
public class DataSourceContextHolder {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceContextHolder.class);
    
    private static final ThreadLocal<DBType> HOLDER = new ThreadLocal<>();

    /***
     * 设置路由key
     * @Author      qinwei
     * @Param       [dbType]
     * @Return      void
     * @Exception
     * @Date         2018/9/3 0003 下午 4:18
    */
    public static void set(DBType dbType) {
        if (dbType == null) throw new NullPointerException();
        DBType old = DataSourceContextHolder.get();
        if( old != null && old.equals(DBType.MASTER)){
            LOGGER.debug("DataSourceContextHolder.set  old is READWRITE ,so do not  set {}",dbType);
            return ;
        }
        LOGGER.debug("DataSourceContextHolder.set dbType {}",dbType);
        HOLDER.set(dbType);
    }

    /***
     * 获取路由key
     * @Author      qinwei
     * @Param       [dbType]
     * @Return      void
     * @Exception
     * @Date         2018/9/3 0003 下午 4:18
     */
    public static DBType get() {
        DBType tmp = HOLDER.get() ;
        LOGGER.debug("DataSourceContextHolder.get dbType {}",tmp);
        return tmp;
    }

    /***
     * 清除路由key，防止内存溢出
     * @Author      qinwei
     * @Param       [dbType]
     * @Return      void
     * @Exception
     * @Date         2018/9/3 0003 下午 4:18
     */
    public static void clear() {
        LOGGER.debug("DataSourceContextHolder.clear");
        HOLDER.remove();
    }

}
