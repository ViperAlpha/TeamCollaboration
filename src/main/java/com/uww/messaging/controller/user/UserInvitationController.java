package com.uww.messaging.controller.user;

import com.google.gson.Gson;
import com.uww.messaging.contract.MessageService;
import com.uww.messaging.contract.TeamService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.Invitation;
import com.uww.messaging.display.Response;
import com.uww.messaging.display.UserInvitationForm;
import com.uww.messaging.display.UserInviteAccept;
import com.uww.messaging.model.Team;
import com.uww.messaging.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private UserService userService;
    private TeamService teamService;
    private MessageService messageService;

    @Autowired
    public UserInvitationController(UserService userService, TeamService teamService, MessageService messageService) {
        this.userService = userService;
        this.teamService = teamService;
        this.messageService = messageService;
    }


    @RequestMapping(value = "/autocomplete-user/list", method = RequestMethod.GET)
    @ResponseBody
    public String autocompleteUserList(Authentication authentication, @RequestParam("usernameToAuto") String usernameToAuto) {
        User loggedInUser = userService.userByAuthentication(authentication);
        List<String> usernamesLackingInvitations = new ArrayList<>();
        // using this to not break compatibility
        userService.findUsersLackingInvitationsStartingWith(loggedInUser.getUserId(),
                usernameToAuto).forEach(x -> usernamesLackingInvitations.add(x.getUsername()));
        return new Gson().toJson(usernamesLackingInvitations);
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

    @RequestMapping(value = "/searchBarInvite", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity sendInvitation(Authentication authentication, @RequestBody Invitation invitation) {
        int loggedInUser = userService.userByAuthentication(authentication)
                .getUserId();
        if (invitation.isIndvidualInvite()) {
            userService.sendInvitation(loggedInUser, new UserInvitationForm(invitation.getName(), invitation.getMessage()));
            return new ResponseEntity(HttpStatus.OK);
        } else if (invitation.isTeamInvite()) {
            Team team = teamService.findTeamByTeamName(invitation.getTeamName());
            teamService.inviteMemberToTeam(team.getTeamId(), loggedInUser, invitation.getName(), invitation.getMessage());
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
