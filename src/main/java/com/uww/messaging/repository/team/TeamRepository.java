package com.uww.messaging.repository.team;

import com.uww.messaging.model.team.Team;
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
    List<Team> findByTeamNameStartingWith(String teamName);
    List<Team> findByTeamName(String teamName);
}
