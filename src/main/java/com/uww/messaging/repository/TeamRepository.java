package com.uww.messaging.repository;

import com.uww.messaging.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by horvste on 2/19/16.
 */
@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
    List<Team> findByTeamIdIn(Collection<Integer> teamIds);
}
