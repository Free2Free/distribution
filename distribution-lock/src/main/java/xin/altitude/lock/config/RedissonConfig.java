package xin.altitude.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author explore
 * @date 2020/11/03 12:43
 */
@Configuration
public class RedissonConfig {
    @Value("${redisson.address}")
    private String addressUrl;

    /**
     * 配置redis连接（无密码版）
     *
     * @return
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(addressUrl)
                //.setPassword(password)
                .setReconnectionTimeout(10000)
                .setRetryInterval(5000)
                .setTimeout(10000)
                .setConnectTimeout(10000);
        return Redisson.create(config);
    }
}
