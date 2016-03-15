package com.uww.messaging.service;

import com.uww.messaging.contract.MessageService;
import com.uww.messaging.model.User;
import com.uww.messaging.model.UserMessage;
import com.uww.messaging.model.UserMessageChat;
import com.uww.messaging.repository.UserMessageChatRepository;
import com.uww.messaging.repository.UserMessageRepository;
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

    @Override
    public List<UserMessage> findMessagesBetweenUsers(int userId, int secondUserId) {
        List<UserMessageChat> chatsByUserId = userMessageChatRepository.findChatsByUserId(userId, secondUserId);
        sizeGreaterThanOneThrowException(chatsByUserId, userId, secondUserId);
        if (chatsByUserId.size() == 0)
            return new ArrayList<>();
        return userMessageRepository.findByUserMessageChatIdOrderByMessageTimeAsc(chatsByUserId.get(0).getUserMessageChatId());
    }

    @Transactional
    @Override
    public void haveIndividualConversation(int currentUserId, int toUserId, String message) {
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
            return;
        }
        UserMessage userMessage = new UserMessage(currentUserId, toUserId, message, chatsByUserId.get(0).getUserMessageChatId(),
                currentTimestamp);
        userMessageRepository.save(userMessage);

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

}
