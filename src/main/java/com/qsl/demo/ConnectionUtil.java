package com.qsl.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author qiaoshiling
 * @Description TODO
 * @Date 2019/7/17 15:24
 **/
public class ConnectionUtil {

    private static String ipAddress="192.168.10.65:2181";

    public static CuratorFramework getConnection(String nameSpace){

        if(null == nameSpace || "".equals(nameSpace)){
            return CuratorFrameworkFactory.builder().connectString(ipAddress).retryPolicy(new ExponentialBackoffRetry(1000,3))
                    .sessionTimeoutMs(5000).build();
        }else {
            return CuratorFrameworkFactory.builder().connectString(ipAddress).retryPolicy(new ExponentialBackoffRetry(1000,3))
                    .sessionTimeoutMs(5000).namespace(nameSpace).build();
        }
    }


}
