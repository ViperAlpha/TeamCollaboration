package com.uww.messaging.repository.team;

import com.uww.messaging.model.team.TeamMessageChat;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Reinaldo
 */
public interface TeamMessageChatRepository extends CrudRepository<TeamMessageChat, Integer> {
	@Query("SELECT u FROM TeamMessageChat u WHERE (u.teamId=?1)")
	List<TeamMessageChat> findChatsByTeamId(int teamId);
}