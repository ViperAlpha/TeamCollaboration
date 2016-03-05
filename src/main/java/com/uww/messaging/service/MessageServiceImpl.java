package com.uww.messaging.service;

import com.uww.messaging.contract.MessageService;
import com.uww.messaging.model.User;
import com.uww.messaging.model.UserMessage;
import com.uww.messaging.model.UserMessageChat;
import com.uww.messaging.repository.UserMessageChatRepository;
import com.uww.messaging.repository.UserMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
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
        if(chatsByUserId.size() == 0)
            return  new ArrayList<UserMessage>();
        return userMessageRepository.findByUserMessageChatId(chatsByUserId.get(0).getUserMessageChatId());
    }

    private static void sizeGreaterThanOneThrowException(List<UserMessageChat> userMessageChats, int userId, int secondUserId) {
        if (userMessageChats.size() > 1) {
            String errorMessage = MessageFormat.format("Data Corruption: UserMessageChat table has more than one entry for userId: {0} and userId: {1}", userId, secondUserId);
            throw new RuntimeException(errorMessage);
        }
    }

}
