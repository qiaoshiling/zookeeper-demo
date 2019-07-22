package com.qsl.demo.lock;

import com.qsl.demo.ConnectionUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qiaoshiling
 * @Description TODO
 * @Date 2019/7/17 15:23
 * 使用zookeeper 分布式锁功能
 **/
public class LockDemo {

    public static void main(String[] args) {

        try{
            CuratorFramework connection = ConnectionUtil.getConnection("lock");
            List<String> path = new ArrayList<>();
            path.add("/01");
            InterProcessMultiLock interProcessMultiLock = new InterProcessMultiLock(connection,path);
            connection.start();
            lock(interProcessMultiLock);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void lock(InterProcessMultiLock interProcessMultiLock) {
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"---尝试获取锁");
                try {
                    interProcessMultiLock.acquire();
                    System.out.println(Thread.currentThread().getName()+"---获取锁");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        interProcessMultiLock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },"Thread-"+i).start();

        }
    }

}
