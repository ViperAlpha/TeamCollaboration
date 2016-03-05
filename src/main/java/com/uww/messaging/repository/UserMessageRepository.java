package com.uww.messaging.repository;

import com.uww.messaging.model.UserMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by horvste on 3/5/16.
 */
@Repository
public interface UserMessageRepository extends CrudRepository<UserMessage, Integer> {
    List<UserMessage> findByUserMessageChatIdOrderByMessageTime(int userMessageChatId);
}
