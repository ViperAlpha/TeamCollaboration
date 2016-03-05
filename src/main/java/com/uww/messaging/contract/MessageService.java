package com.uww.messaging.contract;

import com.uww.messaging.model.UserMessage;

import java.util.List;

/**
 * Created by horvste on 3/5/16.
 */
public interface MessageService {
    List<UserMessage> findMessagesBetweenUsers(int userId, int secondUserId);
    void haveIndividualConversation(int currentUserId, int toUserId, String message);
}
