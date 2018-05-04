package org.rabbit.mvc.data;

import org.rabbit.mvc.Spittle;
import org.rabbit.mvc.web.DuplicateSpittleException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.security.RolesAllowed;

import java.util.List;

public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    @Cacheable(value = "spitterCache", unless = "#result.message.contains('NoCache')", condition = "#id >= 10")
    Spittle findOne(long spittleId);

    @CachePut(value = "spitterCache", key = "#result.id")
    Spittle save(Spittle spittle) throws DuplicateSpittleException;

    @CacheEvict("spittleCache")
    void remove(long spittleId);

//    @RolesAllowed("ROLE_SPITTER")
    @PreAuthorize("hasRole('ROLE_SPITTER') and #spittle.text.length() <= 140" + "or hasRole('ROLE_PREMIUM')")
    void addSpittle(Spittle spittle);

    @PreAuthorize("hasRole({'ROLE_SPITTER', 'ROLE_ADMIN'})")
    @PostFilter("hasRole('ROLE_ADMIN') || "
                + "filterObject.spitter.username == principal.username")
    List<Spittle> getOffensiveSpittles();

}
