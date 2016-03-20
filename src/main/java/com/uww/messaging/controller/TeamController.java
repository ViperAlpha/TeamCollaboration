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
@RequestMapping(value = "user/team")
public class TeamController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public String createTeam(Authentication authentication, @RequestParam("teamName") String teamName, @RequestParam("teamDescription") String teamDescription) {
        User currentUser = userService.userByAuthentication(authentication);
        teamService.save(currentUser.getUserId(), teamName, teamDescription);
        return "redirect:/user";
    }

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public String listTeams(Authentication authentication){


		User currentUser = userService.userByAuthentication(authentication);
		Gson gson = new Gson();
		List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
		return "teams : "  + gson.toJson(teams);
	}

	@RequestMapping(value = "/invite", method = RequestMethod.PUT)
	public String inviteToTeam(Authentication authentication, @RequestParam("teamId") int teamId, @RequestParam("invitedUserId") int invitedUserId){

		User currentUser = userService.userByAuthentication(authentication);
		teamService.addTeamMember(teamId,currentUser.getUserId(),invitedUserId);

		return "redirect:/user";
	}
}
