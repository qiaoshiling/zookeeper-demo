package com.qsl.demo.acl;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author qiaoshiling
 * @Description TODO
 * @Date 2019/7/17 15:11
 * zookeeper 关于acl权限控制的简单操作
 **/
public class AclDemo {
    private static  String ipAddress="192.168.10.65:2181";
    public static void main(String[] args) {

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().retryPolicy(new ExponentialBackoffRetry(1000, 3)).connectString(ipAddress)
                .sessionTimeoutMs(5000).build();
    }
}
