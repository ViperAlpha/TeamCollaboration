package com.uww.messaging.controller;

import com.google.gson.Gson;
import com.uww.messaging.contract.TeamService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.model.Team;
import com.uww.messaging.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

	private static final String redirectHome = "redirect:/user";

	@RequestMapping(value = "/invitation/mine",method = RequestMethod.GET)
	@ResponseBody
	public String getTeamInvitations(Authentication authentication){

		int userId = userService.userByAuthentication(authentication).getUserId();
		return new Gson().toJson(teamService.findAllInvitationsToUser(userId));
	}

	@RequestMapping(value = "/add/user", method = RequestMethod.PUT)
	public String addUserToTeam(Authentication authentication, @RequestParam("") String userName, int toTeamId){

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
	public String listTeams(Authentication authentication){
		User currentUser = userService.userByAuthentication(authentication);
		Gson gson = new Gson();
		List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
		return gson.toJson(teams);
	}

	@RequestMapping(value = "/invite", method = RequestMethod.PUT)
	public String inviteToTeam(Authentication authentication, @RequestParam("teamId") int teamId, @RequestParam("invitedUserName") String invitedUsername, @RequestParam("message") String message){

		User currentUser = userService.userByAuthentication(authentication);
		teamService.inviteMemberToTeam(teamId,currentUser.getUserId(),invitedUsername,message);

		return "redirect:/user";
	}

	@RequestMapping(value = "/invite/accept", method = RequestMethod.PUT)
	public String acceptInvitation(Authentication authentication, @RequestParam("teamInvitationId") int teamInvitationId){
		teamService.acceptTeamInvitation(teamInvitationId);
		return "redirect:/user";
	}

	@RequestMapping(value = "/invite/reject", method = RequestMethod.PUT)
	public String rejectInvitation(Authentication authentication, @RequestParam("teamInvitationId") int teamInvitationId){
		teamService.rejectTeamInvitation(teamInvitationId);
		return "redirect:/user";
	}
}
