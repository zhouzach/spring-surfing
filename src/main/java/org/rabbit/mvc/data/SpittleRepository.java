package org.rabbit.mvc.data;

import org.rabbit.mvc.Spittle;
import org.rabbit.mvc.web.DuplicateSpittleException;

import java.util.List;

public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long spittleId);

    void save(Spittle spittle) throws DuplicateSpittleException;


}
