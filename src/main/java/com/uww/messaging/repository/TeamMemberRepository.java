package com.uww.messaging.repository;

import com.uww.messaging.model.TeamMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by horvste on 2/20/16.
 */
@Repository
public interface TeamMemberRepository extends CrudRepository<TeamMember, Integer>{
    List<TeamMember> findTeamMemberByUserId(int userId);
}
