package com.qsl.demo.watch;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qiaoshiling
 * @Description TODO
 * @Date 2019/7/19 9:25
 **/
public class WatchDemo {
    private static String ipAddress="192.168.10.65:2181";
    public static void main(String[] args) {

       try{
          // ZkClient zkClient = new ZkClient(ipAddress);

           ZooKeeper zooKeeper = new ZooKeeper(ipAddress, 4000, new Watcher() {
               @Override
               public void process(WatchedEvent watchedEvent) {
                   System.out.println("监听事件");
                   System.out.println(watchedEvent.toString());
               }
           });

           zooKeeper.create("/create","test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
           zooKeeper.exists("/create",true);
           System.out.println("修改事件");
           Stat stat = new Stat();
           zooKeeper.setData("/create","test".getBytes(),stat.getVersion());
            System.in.read();

       }catch (Exception e){
           e.printStackTrace();
       }

    }
}
