package com.qsl.demo.leader;

import com.qsl.demo.ConnectionUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;

/**
 * @Author qiaoshiling
 * @Description TODO
 * @Date 2019/7/17 15:22
 * 使用zookeeper leader选举功能
 **/
public class LeaderDemo {

    public static void main(String[] args) {
        CuratorFramework curatorFramework = ConnectionUtil.getConnection("leader");
        curatorFramework.start();

        LeaderSelectorListener leaderSelectorListener = new LeaderSelectorListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }

            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {

            }
        };
        LeaderSelector leaderSelector= new LeaderSelector(curatorFramework,"/leader",leaderSelectorListener);
        leaderSelector.start();



    }

}
