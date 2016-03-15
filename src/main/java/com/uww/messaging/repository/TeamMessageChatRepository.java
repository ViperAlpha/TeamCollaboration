package com.uww.messaging.repository;

import com.uww.messaging.model.TeamMessageChat;
import com.uww.messaging.model.UserMessageChat;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Reinaldo
 */
public interface TeamMessageChatRepository extends CrudRepository<TeamMessageChat, Integer> {
    @Query("SELECT u FROM TeamMessageChat t WHERE (t.teamId=?1)")
    List<TeamMessageChat> findChatsByTeamId(int teamId);
}