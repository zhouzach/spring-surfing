package org.rabbit.mvc.data;

import org.rabbit.mvc.Spittle;
import org.rabbit.mvc.web.DuplicateSpittleException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    @Cacheable(value = "spitterCache", unless = "#result.message.contains('NoCache')", condition = "#id >= 10")
    Spittle findOne(long spittleId);

    @CachePut(value = "spitterCache", key = "#result.id")
    Spittle save(Spittle spittle) throws DuplicateSpittleException;

    @CacheEvict("spittleCache")
    void remove(long spittleId);


}
