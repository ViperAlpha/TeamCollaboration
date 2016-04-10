package com.uww.messaging.repository.team;

import com.uww.messaging.model.team.TeamMessage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Reinaldo
 */
@Repository
public interface TeamMessageRepository extends CrudRepository<TeamMessage, Integer> {
	@Query("SELECT u FROM TeamMessage u, TeamMessageChat c WHERE (c.teamMessageChatId=?1 AND u.toTeamId=c.teamId) order by u.messageTime")
	List<TeamMessage> findByTeamMessageChatIdOrderByMessageTimeAsc(int teamMessageChatId);

	@Query("SELECT u FROM TeamMessage u WHERE (u.toTeamId=?1 AND u.messageTime>?2)")
	List<TeamMessage> findNewMessages(int teamId, Timestamp firstTime, Timestamp lastTime);
}
