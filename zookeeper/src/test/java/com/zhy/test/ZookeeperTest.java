package com.zhy.test;

import com.zhy.zookeeper.client.ZkClientx;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/5/27 11:19
 */
public class ZookeeperTest {

    private ZkClient zkClient;


    @Test
    public ZkClient client() {
        ZkClient zkClient = new ZkClient("127.0.0.1:21810");
        return zkClient;
    }

    public ZkClientx clienttx() {
        ZkClientx zkClient = new ZkClientx("127.0.0.1:21810");
        return zkClient;
    }

    /**
     * 创建节点
     */
    @Test
    public void creatNode() {
        client().create("/testnode1", "11111", CreateMode.PERSISTENT);

    }

    /**
     * 创建节点
     */
    @Test
    public void creatNode2() {
        clienttx().createPersistent("/testnode1/chi", "4444", true);

    }

    @Test
    public void data1() {
        List<String> children = clienttx().getChildren("/testnode1");
        for (String child : children) {
            System.out.println(child);
        }

    }

    @Test
    public void data() {
        String s = clienttx().readDataString("/testnode1/chi");

        System.out.println(s);
    }

    @Test
    public void data2() {
        clienttx().writeData("/testnode1/chi", 43333);

    }

    @Test
    public void watch() throws IOException {
        clienttx().subscribeDataChanges("/testnode1/chi", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(dataPath);
                System.out.println(data);

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {

            }
        });

        System.in.read();

    }


    @Test
    public void close() {
        zkClient.close();
    }

}
