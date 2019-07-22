package com.qsl.demo.simpleoperation;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author qiaoshiling
 * @Description TODO
 * @Date 2019/7/17 14:06
 * 使用curator 简单操作
 **/
public class SimpleOperation {

    private static  String ipAddress="192.168.10.65:2181";

    public static void main(String[] args) {

     try{
         CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(ipAddress).retryPolicy(new ExponentialBackoffRetry(100,3)).build();
         curatorFramework.start();
          //new SimpleOperation().add(curatorFramework);
         //new SimpleOperation().update(curatorFramework);
         //new SimpleOperation().select(curatorFramework);
         new SimpleOperation().delete(curatorFramework);
     }catch (Exception e){
         e.printStackTrace();
     }

    }

    /**
     * 增
     * @param curatorFramework
     */
    public void  add(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.create().creatingParentsIfNeeded().forPath("/qsl","test".getBytes());
    }



    /**
     * 改
     * @param curatorFramework
     */
    public void update(CuratorFramework curatorFramework) throws Exception{
        curatorFramework.setData().forPath("/qsl","test01".getBytes());
    }

    /**
     * 查
     * @param curatorFramework
     */
    public void select(CuratorFramework curatorFramework) throws  Exception{
        byte[] bytes = curatorFramework.getData().forPath("/qsl");
        System.out.println(new String(bytes));
    }

    /**
     * 删
     * @param curatorFramework
     */
    public void delete(CuratorFramework curatorFramework) throws  Exception{
        curatorFramework.delete().forPath("/qsl");
    }

}
