package com.uww.messaging.repository;

import com.uww.messaging.model.TeamMessage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Reinaldo
 */
@Repository
public interface TeamMessageRepository extends CrudRepository<TeamMessage, Integer> {
	List<TeamMessage> findByTeamMessageChatIdOrderByMessageTimeAsc(int teamMessageChatId);
}
