package com.uww.messaging.repository.wiki;

import com.uww.messaging.model.wiki.Wiki;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by horvste on 4/9/16.
 */
public interface WikiRepository extends CrudRepository<Wiki, Integer> {
    List<Wiki> findAllByOrderByEditTime();
}
