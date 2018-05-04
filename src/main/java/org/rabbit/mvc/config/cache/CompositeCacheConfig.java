package org.rabbit.mvc.config.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableCaching
@Import({RedisCacheConfig.class})
public class CompositeCacheConfig {
    @Bean
    public javax.cache.CacheManager cache() {
        CachingProvider cachingProvider = Caching. getCachingProvider();
        return cachingProvider.getCacheManager();
    }

    @Bean
    public CacheManager cacheManager(net.sf.ehcache.CacheManager cm,
                                     javax.cache.CacheManager jcm,
                                     RedisTemplate redisTemplate) {
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        List<CacheManager> managers = new ArrayList<CacheManager>();
        managers.add(new JCacheCacheManager(jcm));
        managers.add(new EhCacheCacheManager(cm));
        managers.add(new RedisCacheManager(redisTemplate));
        cacheManager.setCacheManagers(managers);

        return cacheManager;
    }
}
