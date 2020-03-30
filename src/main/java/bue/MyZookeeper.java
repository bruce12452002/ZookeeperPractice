package bue;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class MyZookeeper {
    private static final String CONNECT_PATH = "localhost:2181";
    private static final String ZNODE_PATH = "/xxx";
    private static final int SESSION_TIMEOUT = 10_000;
    private static ZooKeeper zk;

    static {
        try {
            zk = new ZooKeeper(CONNECT_PATH, SESSION_TIMEOUT, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createZnode(String path, String value) throws KeeperException, InterruptedException {
        zk.create(path, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public String getZnode(String path) throws KeeperException, InterruptedException {
        byte[] data = zk.getData(path, false, new Stat());
        return new String(data);
    }

    public void close() throws InterruptedException {
        if (zk != null) zk.close();
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        MyZookeeper myZookeeper = new MyZookeeper();

        if (zk.exists(ZNODE_PATH, false) == null) {
            myZookeeper.createZnode(ZNODE_PATH, "I'm xxx's node");
            String value = myZookeeper.getZnode(ZNODE_PATH);
            System.out.println("data=" + value);
        }
        myZookeeper.close();
    }
}
