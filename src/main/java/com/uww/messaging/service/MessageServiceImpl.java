package com.uww.messaging.service;

import com.uww.messaging.contract.MessageService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.TeamMessageDisplay;
import com.uww.messaging.display.UserMessageDisplay;
import com.uww.messaging.model.team.TeamMessage;
import com.uww.messaging.model.team.TeamMessageChat;
import com.uww.messaging.model.team.TeamUploadedFile;
import com.uww.messaging.model.user.User;
import com.uww.messaging.model.user.UserMessage;
import com.uww.messaging.model.user.UserMessageChat;
import com.uww.messaging.model.user.UserUploadedFile;
import com.uww.messaging.repository.team.TeamMessageChatRepository;
import com.uww.messaging.repository.team.TeamMessageRepository;
import com.uww.messaging.repository.team.TeamUploadedFileRepository;
import com.uww.messaging.repository.user.UserMessageChatRepository;
import com.uww.messaging.repository.user.UserMessageRepository;
import com.uww.messaging.repository.user.UserUploadedFileRepository;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private UserMessageRepository userMessageRepository;

    private UserMessageChatRepository userMessageChatRepository;

    private UserUploadedFileRepository userUploadedFileRepository;

    private UserService userService;

    private TeamMessageRepository teamMessageRepository;

    private TeamMessageChatRepository teamMessageChatRepository;

    private TeamUploadedFileRepository teamUploadedFileRepository;

    @Autowired
    public MessageServiceImpl(UserMessageRepository userMessageRepository, UserMessageChatRepository userMessageChatRepository, UserUploadedFileRepository userUploadedFileRepository, UserService userService, TeamMessageRepository teamMessageRepository, TeamMessageChatRepository teamMessageChatRepository, TeamUploadedFileRepository teamUploadedFileRepository) {
        this.userMessageRepository = userMessageRepository;
        this.userMessageChatRepository = userMessageChatRepository;
        this.userUploadedFileRepository = userUploadedFileRepository;
        this.userService = userService;
        this.teamMessageRepository = teamMessageRepository;
        this.teamMessageChatRepository = teamMessageChatRepository;
        this.teamUploadedFileRepository = teamUploadedFileRepository;
    }

    @Override
    public List<UserMessageDisplay> findMessagesBetweenUsers(int userId, int secondUserId) {
        List<UserMessageChat> chatsByUserId = userMessageChatRepository.findChatsByUserId(userId, secondUserId);
        sizeGreaterThanOneThrowException(chatsByUserId, userId, secondUserId);
        if (chatsByUserId.size() == 0) {
            return new ArrayList<>();
        }
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
    public List<TeamMessageDisplay> findMessagesFromTeam(final int teamId) {
        List<TeamMessageChat> chatsByTeamId = teamMessageChatRepository.findChatsByTeamId(teamId);

        if (chatsByTeamId.size() == 0) {
            return new ArrayList<>();
        }

        List<TeamMessage> teamMessages = teamMessageRepository.findByTeamMessageChatIdOrderByMessageTimeAsc(chatsByTeamId.get(0).getTeamMessageChatId());

        List<TeamMessageDisplay> msgsDisplay = new ArrayList<>();

        teamMessages.forEach(msg -> {
            User user = userService.findUserById(msg.getFromUserId());
            List<TeamUploadedFile> teamUploadedFiles = teamUploadedFileRepository.findByTeamMessageChatId(msg.getTeamMessageId());
            TeamMessageDisplay tmd = new TeamMessageDisplay(msg.getTeamMessageId(),
                    msg.getFromUserId(),
                    user.getUsername(),
                    user.getFirstName(),
                    msg.getMessage(),
                    msg.getMessageTime(),
                    teamUploadedFiles.size() == 0 ? "null" : teamUploadedFiles.get(0).getFileName());
            msgsDisplay.add(tmd);
        });

        return msgsDisplay;

    }

    @Transactional
    @Override
    public void sendMessageToTeam(final int fromUserId, final int teamId, final String message) {

        sendMessageToTeamGetTeamMessageId(fromUserId, teamId, message);
    }

    private int sendMessageToTeamGetTeamMessageId(int fromUserId, int teamId, String message) {
        List<TeamMessageChat> chatsByTeamId = teamMessageChatRepository.findChatsByTeamId(teamId);

        Timestamp time = new Timestamp(new Date().getTime());
        ifZeroChatsCreate(teamId, chatsByTeamId, time);
        TeamMessage teamMessage = new TeamMessage(fromUserId, teamId, message, time);
        teamMessageRepository.save(teamMessage);
        return teamMessage.getTeamMessageId();
    }

    private void ifZeroChatsCreate(int teamId, List<TeamMessageChat> chatsByTeamId, Timestamp time) {
        if (chatsByTeamId.size() == 0) {
            TeamMessageChat chat = new TeamMessageChat(teamId, time);
            teamMessageChatRepository.save(chat);
        }
    }

    @Override
    public void sendMessageToTeam(int fromUserId, int toTeamId, String message, MultipartFile multiPartFile, String saveBaseDirectory) throws IOException {
        if (multiPartFile.isEmpty()) {
            sendMessageToTeam(
                    fromUserId,
                    toTeamId,
                    message
            );
            return;
        }


        if (!new File(saveBaseDirectory).exists()) {
            throw new RuntimeException("You did not setup a user download directory in your application.properties file. This directory does not exist: "
                    + saveBaseDirectory);
        }

        String randomAlphaNumeric = RandomStringUtils.randomAlphabetic(8);
        String fileSavePath = saveBaseDirectory + "/" + randomAlphaNumeric;
        File file = new File(fileSavePath);
        if (file.exists()) {
            randomAlphaNumeric = RandomStringUtils.randomAlphabetic(8);
            fileSavePath = saveBaseDirectory + "/" + randomAlphaNumeric;
            file = new File(fileSavePath);
            if (file.exists()) {
                String randomlyGeneratedFileNameErrorMessage = MessageFormat.format("Attempted to generate random name: {0} for file {1} but {0} exists", randomAlphaNumeric, multiPartFile.getOriginalFilename());
                throw new RuntimeException(randomlyGeneratedFileNameErrorMessage);
            }
        }
        multiPartFile.transferTo(file);


        int teamMessageChat = sendMessageToTeamGetTeamMessageId(fromUserId, toTeamId, message);

        TeamUploadedFile teamUploadedFile = new TeamUploadedFile(fromUserId, multiPartFile.getOriginalFilename(), file.getAbsolutePath(), teamMessageChat);
        teamUploadedFileRepository.save(teamUploadedFile);
    }

    //TODO: NEEDS LOGOUT TIME, CURRENTLY, LASTLOGGEDIN Is log in time.
    @Override
    public List<TeamMessage> findNewMessagesFromTeam(final User user, final int teamId) {

        return teamMessageRepository.findNewMessages(teamId, user.getLastLoggedIn(), new Timestamp(System.currentTimeMillis()));
    }

}
