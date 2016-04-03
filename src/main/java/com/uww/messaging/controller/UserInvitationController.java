package com.uww.messaging.controller;

import com.google.gson.Gson;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.Response;
import com.uww.messaging.display.UserInvitationForm;
import com.uww.messaging.display.UserInviteAccept;
import com.uww.messaging.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by horvste on 3/5/16.
 */
@Controller
@RequestMapping(value = "/user/invitation")
public class UserInvitationController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/autocomplete-user/list", method = RequestMethod.GET)
    @ResponseBody
    public String autocompleteUserList(Authentication authentication, @RequestParam("usernameToAuto") String usernameToAuto) {
        User loggedInUser = userService.userByAuthentication(authentication);
        List<String> usernamesLackingInvitations = userService.findUsersLackingInvitationsStartingWith(loggedInUser.getUserId(),
                usernameToAuto);
        return new Gson().toJson
                (usernamesLackingInvitations);
    }

    @RequestMapping(value = "/mine", method = RequestMethod.GET)
    @ResponseBody
    public String seeMyInvitations(Authentication authentication) {
        int myUserId = userService.userByAuthentication(authentication).getUserId();
        Gson gson = new Gson();
        return gson.toJson(userService.findAllPendingInvitations(myUserId));
    }

    @RequestMapping(value = "/invite", method = RequestMethod.PUT)
    @ResponseBody
    public String inviteUser(Authentication authentication, @RequestBody UserInvitationForm userInvitationForm) {
        int userId = userService.userByAuthentication(authentication).getUserId();
        userService.sendInvitation(userId, userInvitationForm);
        Gson gson = new Gson();
        Response invitationResponse = new Response(
                true,
                "Send invitation to: " + userInvitationForm.getName(),
                ""
        );
        return gson.toJson(invitationResponse);
    }

    @RequestMapping(value = "/accept", method = RequestMethod.PUT)
    @ResponseBody
    public String acceptInvite(Authentication authentication,
                               @RequestBody UserInviteAccept userInviteAccept) {
        int loggedInUserId = userService.userByAuthentication(authentication).getUserId();
        userService.acceptInvitation(loggedInUserId, userInviteAccept.getFromUserId(), userInviteAccept.getUserInvitationId());
        Gson gson = new Gson();
        Response acceptInvitationResponse = new Response(
                true,
                "Accept invitation: FromUserId->" + userInviteAccept.getFromUserId(),
                ""
        );
        return gson.toJson(acceptInvitationResponse);
    }

    @RequestMapping(value = "/see/accepted")
    @ResponseBody
    public String seeAcceptedInvitations(Authentication authentication) {
        int userId = userService.userByAuthentication(authentication).getUserId();
        List<User> acceptedInvitationUsers = userService.findAcceptedInvitationUsers(userId);
        return new Gson().toJson(acceptedInvitationUsers);
    }
}
