package com.upnet.iot.framework.idempotent.config;

import com.upnet.iot.framework.idempotent.core.aop.IdempotentAspect;
import com.upnet.iot.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.upnet.iot.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.upnet.iot.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.upnet.iot.framework.idempotent.core.keyresolver.impl.UserIdempotentKeyResolver;
import com.upnet.iot.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import com.upnet.iot.framework.redis.config.UpnetRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = UpnetRedisAutoConfiguration.class)
public class UpnetIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public UserIdempotentKeyResolver userIdempotentKeyResolver() {
        return new UserIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
