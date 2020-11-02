package xin.altitude.lock.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author explore
 * @date 2020/11/02 13:57
 */
@Configuration
public class MPConfig {

    /**
     * 乐观锁配置
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
