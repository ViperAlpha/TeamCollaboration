package com.uww.messaging.controller;

import com.google.gson.Gson;
import com.uww.messaging.contract.MessageService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.UserMessageDisplay;
import com.uww.messaging.model.TeamMessage;
import com.uww.messaging.model.User;
import com.uww.messaging.model.UserMessage;
import com.uww.messaging.model.UserMessageChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by horvste on 3/1/16.
 */
@Controller
@RequestMapping(value = "/user/message")
public class UserMessageController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/team-messages/list") //add parameters
    @ResponseBody
    public String listTeamMessages() {
        //SHOULD CALL MESSAGING SERVICE, You will need to change database schema

        //should return a json list of team messages depending on GET parameter
        //Gson gson = new Gson();
        //String json = gson.toJson(new Object());
        return "";
    }

    @RequestMapping(value = "/team-messages/insert") //add parameters
    @ResponseBody
    public String insertTeamMessage() {
        //SHOULD CALL MESSAGING SERVICE, You will need to change database schema
        //should insert based on PUT
        //TeamMessage
        //Gson gson = new Gson();
        //String json = gson.toJson(new Object());
        return "";
    }

    /**
     * Access this method after logging in like this:
     * <p>
     * http://localhost:8080/user/message/individual-message/list/?firstUserId={sampleUserId}&secondUserId={sampleUserIdTwo}
     *
     * @param userId
     * @param secondUserId
     * @return string of json which represents a list of messages between users. In the event that there are no messages, we return
     * an empty list.
     */
    @RequestMapping(value = "/individual-message/list", method = RequestMethod.GET)
    @ResponseBody
    public String listIndividualMessages(@RequestParam("firstUserId") int userId, @RequestParam("secondUserId") int secondUserId) {
        Gson gson = new Gson();
        List<UserMessage> messagesBetweenUsers = messageService.findMessagesBetweenUsers(userId, secondUserId);
        return gson.toJson(messagesBetweenUsers);
    }

    @RequestMapping(value = "/individual-message/listBySingleUserId", method = RequestMethod.GET)
    @ResponseBody
    public String listIndividualMessages(Authentication authentication, @RequestParam("userId") int firstUserId) {
        Gson gson = new Gson();
        int userId = userService.userByAuthentication(authentication).getUserId();
        List<UserMessage> messagesBetweenUsers = messageService.findMessagesBetweenUsers(userId, firstUserId);
        List<UserMessageDisplay> userMessageDisplays = new ArrayList<>();
        messagesBetweenUsers.forEach(userMessageChat -> {
            User fromUser = userService.findUserById(userMessageChat.getFromUserId());
            User toUser = userService.findUserById(userMessageChat.getToUserId());
            userMessageDisplays.add(new UserMessageDisplay(
                    userMessageChat.getMessage(),
                    fromUser.getFirstName(),
                    toUser.getFirstName(),
                    userMessageChat.getMessageTime()
                )
            );
        });
        return gson.toJson(userMessageDisplays);
    }

    @RequestMapping(value = "/individual-message/listUsers", method = RequestMethod.GET)
    @ResponseBody
    public String listIndividualMessages(Authentication authentication) {
        Gson gson = new Gson();
        int userId = userService.userByAuthentication(authentication).getUserId();
        List<User> users = new ArrayList<>();
        List<UserMessageChat> messagesBetweenUsers = messageService.findUserMessages(userId);
        messagesBetweenUsers.forEach(userMessageChat -> {
            if (userMessageChat.getFromUserId() == userId) {
                users.add(userService.findUserById(userMessageChat.getToUserId()));
            } else {
                users.add(userService.findUserById(userMessageChat.getFromUserId()));
            }
        });
        return gson.toJson(users);
    }

    @RequestMapping(value = "/individual-message/insert", method = RequestMethod.PUT)
    public String insertIndividualMessages(Authentication authentication, @RequestParam("toUserId") int toUserId, @RequestParam("message") String message) {
        int currentUserId = userService.userByAuthentication(authentication).getUserId();
        messageService.haveIndividualConversation(
                currentUserId,
                toUserId,
                message
        );
        return "redirect:/user";
    }


}
