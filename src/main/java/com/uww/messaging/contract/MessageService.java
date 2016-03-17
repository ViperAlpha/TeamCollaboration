package com.uww.messaging.contract;

import com.uww.messaging.model.TeamMessage;
import com.uww.messaging.model.UserMessage;
import com.uww.messaging.model.UserMessageChat;

import java.util.List;

/**
 * Created by horvste on 3/5/16.
 * <p>
 * Responsible for any message relating functionality. This includes TeamMessages and Indvidual Messages.
 */
public interface MessageService {
    List<UserMessage> findMessagesBetweenUsers(int userId, int secondUserId);

    void haveIndividualConversation(int currentUserId, int toUserId, String message);

    List<UserMessageChat> findUserMessages(int userId);

    List<TeamMessage> findMessagesFromTeam(int teamId);

    void sendMessageToTeam(int fromUserId, int toTeamId, String message);
}
