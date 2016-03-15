package com.uww.messaging.repository;

import com.uww.messaging.model.UserMessageChat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by horvste on 3/5/16.
 */
public interface UserMessageChatRepository extends CrudRepository<UserMessageChat, Integer> {
    @Query("SELECT u FROM UserMessageChat u WHERE (u.fromUserId=?1 AND u.toUserId=?2) OR (u.fromUserId=?2 AND u.toUserId=?1)")
    List<UserMessageChat> findChatsByUserId(int fromUserId, int toUserId);

    @Query("SELECT u FROM UserMessageChat u WHERE u.fromUserId=?1 OR u.toUserId=?1")
    List<UserMessageChat> findChatsByUserId(int userId);
}
