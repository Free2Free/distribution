package xin.altitude.lock.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author explore
 * @date 2020/11/03 10:16
 */
@Configuration
public class ZkConfig {

    /**
     * zk连接配置
     * @return
     */
    @Bean
    public CuratorFramework cf() {
        RetryPolicy retrypolicy = new ExponentialBackoffRetry(3000, 2);
        CuratorFramework curatorframework = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(retrypolicy)
                .build();
        curatorframework.start();

        return curatorframework;
    }

}
