package com.uww.messaging.contract;

import com.uww.messaging.display.TeamMessageDisplay;
import com.uww.messaging.display.UserMessageDisplay;
import com.uww.messaging.model.TeamMessage;
import com.uww.messaging.model.User;
import com.uww.messaging.model.UserMessageChat;
import com.uww.messaging.model.UserUploadedFile;

import java.util.List;

/**
 * Created by horvste on 3/5/16.
 * <p>
 * Responsible for any message relating functionality. This includes TeamMessages and Indvidual Messages.
 */
public interface MessageService {
	List<UserMessageDisplay> findMessagesBetweenUsers(int userId, int secondUserId);

	void haveIndividualConversation(int currentUserId, int toUserId, String message);

	void haveIndividualConversation(int currentUserId, int toUserId, String message, UserUploadedFile userUploadedFile);

	List<UserMessageChat> findUserMessages(int userId);

	List<TeamMessageDisplay> findMessagesFromTeam(int teamId);

	void sendMessageToTeam(int fromUserId, int toTeamId, String message);

	List<TeamMessage> findNewMessagesFromTeam(User user, int teamId);
}
