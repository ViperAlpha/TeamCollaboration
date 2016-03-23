package com.uww.messaging.service;

import com.uww.messaging.contract.MessageService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.UserMessageDisplay;
import com.uww.messaging.model.TeamMessage;
import com.uww.messaging.model.TeamMessageChat;
import com.uww.messaging.model.User;
import com.uww.messaging.model.UserMessage;
import com.uww.messaging.model.UserMessageChat;
import com.uww.messaging.model.UserUploadedFile;
import com.uww.messaging.repository.TeamMessageChatRepository;
import com.uww.messaging.repository.TeamMessageRepository;
import com.uww.messaging.repository.UserMessageChatRepository;
import com.uww.messaging.repository.UserMessageRepository;
import com.uww.messaging.repository.UserUploadedFileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by horvste on 3/5/16.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private UserMessageRepository userMessageRepository;

    @Autowired
    private UserMessageChatRepository userMessageChatRepository;

    @Autowired
    private UserUploadedFileRepository userUploadedFileRepository;

    @Autowired
    private UserService userService;

	@Autowired
	private TeamMessageRepository teamMessageRepository;

	@Autowired
	private TeamMessageChatRepository teamMessageChatRepository;

    @Override
    public List<UserMessageDisplay> findMessagesBetweenUsers(int userId, int secondUserId) {
        List<UserMessageChat> chatsByUserId = userMessageChatRepository.findChatsByUserId(userId, secondUserId);
        sizeGreaterThanOneThrowException(chatsByUserId, userId, secondUserId);
        if (chatsByUserId.size() == 0)
            return new ArrayList<>();
        List<UserMessage> messagesBetweenUsers = userMessageRepository.findByUserMessageChatIdOrderByMessageTimeAsc(chatsByUserId.get(0).getUserMessageChatId());
        List<UserMessageDisplay> userMessageDisplays = new ArrayList<>();
        messagesBetweenUsers.forEach(userMessageChat -> {
            User fromUser = userService.findUserById(userMessageChat.getFromUserId());
            User toUser = userService.findUserById(userMessageChat.getToUserId());
            List<UserUploadedFile> byChatId = userUploadedFileRepository.findByChatId(userMessageChat.getUserMessageId());
            userMessageDisplays.add(new UserMessageDisplay(
                            userMessageChat.getMessage(),
                            fromUser.getFirstName(),
                            toUser.getFirstName(),
                            userMessageChat.getMessageTime(),
                            byChatId.size() == 0 ? "null" : byChatId.get(0).getFileName()
                    )
            );
        });
        return userMessageDisplays;
    }

    @Transactional
    @Override
    public void haveIndividualConversation(int currentUserId, int toUserId, String message) {
        individualConversation(currentUserId, toUserId, message);

    }

    private UserMessage individualConversation(int currentUserId, int toUserId, String message) {
        List<UserMessageChat> chatsByUserId = userMessageChatRepository.findChatsByUserId(currentUserId, toUserId);
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        if (chatsByUserId.size() == 0) {
            UserMessageChat userMessageChat = new UserMessageChat(
                    currentUserId,
                    toUserId,
                    currentTimestamp);
            userMessageChatRepository.save(userMessageChat);
            UserMessage userMessage = new UserMessage(currentUserId, toUserId, message, userMessageChat.getUserMessageChatId(),
                    currentTimestamp);
            userMessageRepository.save(userMessage);
            return userMessage;
        }
        UserMessage userMessage = new UserMessage(currentUserId, toUserId, message, chatsByUserId.get(0).getUserMessageChatId(),
                currentTimestamp);
        userMessageRepository.save(userMessage);
        return userMessage;
    }

    @Transactional
    @Override
    public void haveIndividualConversation(int currentUserId, int toUserId, String message, UserUploadedFile userUploadedFile) {
        User loggedInUser = userService.findUserById(currentUserId);
        UserMessage userMessage = individualConversation(currentUserId, toUserId, message);
        userUploadedFile.setChatId(userMessage.getUserMessageId());
        userUploadedFileRepository.save(userUploadedFile);
    }

    @Override
    public List<UserMessageChat> findUserMessages(int userId) {
        return userMessageChatRepository.findMessageChatsByUserId(userId);
    }

    private static void sizeGreaterThanOneThrowException(List<UserMessageChat> userMessageChats, int userId, int secondUserId) {
        if (userMessageChats.size() > 1) {
            String errorMessage = MessageFormat.format("Data Corruption: UserMessageChat table has more than one entry for userId: {0} and userId: {1}", userId, secondUserId);
            throw new RuntimeException(errorMessage);
        }
    }

	@Override
	public List<TeamMessage> findMessagesFromTeam(final int teamId) {
		List<TeamMessageChat> chatsByTeamId = teamMessageChatRepository.findChatsByTeamId(teamId);

		return (chatsByTeamId.size() == 0) ?
		       new ArrayList<>() :
		       teamMessageRepository.findByTeamMessageChatIdOrderByMessageTimeAsc(chatsByTeamId.get(0).getTeamMessageChatId());

	}

	@Transactional
	@Override
	public void sendMessageToTeam(final int fromUserId, final int teamId, final String message) {

		List<TeamMessageChat> chatsByTeamId = teamMessageChatRepository.findChatsByTeamId(teamId);
		Timestamp time = new Timestamp(new Date().getTime());

		if(chatsByTeamId.size() == 0){
			TeamMessageChat chat = new TeamMessageChat(teamId,time);
			teamMessageChatRepository.save(chat);
		}
		TeamMessage team = new TeamMessage(fromUserId,teamId,message,chatsByTeamId.get(0).getTeamMessageChatId(),time);

		teamMessageRepository.save(team);
	}

	//TODO: NEEDS LOGOUT TIME, CURRENTLY, LASTLOGGEDIN Is log in time.
	@Override
	public List<TeamMessage> findNewMessagesFromTeam(final User user, final int teamId) {

		return teamMessageRepository.findNewMessages(teamId, user.getLastLoggedIn(), new Timestamp(System.currentTimeMillis()));
	}

}
