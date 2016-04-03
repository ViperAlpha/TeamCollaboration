package com.uww.messaging.controller;

import com.google.gson.Gson;
import com.uww.messaging.contract.TeamService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.Response;
import com.uww.messaging.display.TeamInvitationForm;
import com.uww.messaging.model.Team;
import com.uww.messaging.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/user/team")
public class TeamController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    private static final String redirectHome = "redirect:/user";

    @RequestMapping(value = "/invitation/mine", method = RequestMethod.GET)
    @ResponseBody
    public String getTeamInvitations(Authentication authentication) {

        int userId = userService.userByAuthentication(authentication).getUserId();
        return new Gson().toJson(teamService.findAllInvitationsToUser(userId));
    }

    @RequestMapping(value = "/add/user", method = RequestMethod.PUT)
    public String addUserToTeam(Authentication authentication, @RequestParam("") String userName, int toTeamId) {

        //TODO : ADD USER TO A GROUP.

        return redirectHome;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public String createTeam(Authentication authentication, @RequestParam("teamName") String teamName, @RequestParam("teamDescription") String teamDescription) {
        User currentUser = userService.userByAuthentication(authentication);
        teamService.save(currentUser.getUserId(), teamName, teamDescription);
        return redirectHome;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String listTeams(Authentication authentication) {
        User currentUser = userService.userByAuthentication(authentication);
        Gson gson = new Gson();
        List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
        return gson.toJson(teams);
    }


    //TODO: APPROPRIATE RESPONSE LATER.
    @RequestMapping(value = "/invite/send", method = RequestMethod.POST)
    @ResponseBody
    public String inviteToTeam(Authentication authentication, @RequestBody TeamInvitationForm t) {

        User currentUser = userService.userByAuthentication(authentication);

        int teamId = t.getTeamId();
        String invitedUsername = t.getInvitedUserName();
        String message = t.getMessage();

        teamService.inviteMemberToTeam(teamId, currentUser.getUserId(), invitedUsername, message);

        Response invitationResponse = new Response(
                true,
                "Send invitation to: " + t.getInvitedUserName(),
                ""
        );
        return new Gson().toJson(invitationResponse);
    }

    @RequestMapping(value = "/invite/accept", method = RequestMethod.PUT)
    @ResponseBody
    public String acceptInvitation(Authentication authentication, @RequestParam("teamInvitationId") int teamInvitationId) {
        teamService.acceptTeamInvitation(teamInvitationId);
        return "redirect:/user";
    }

}
