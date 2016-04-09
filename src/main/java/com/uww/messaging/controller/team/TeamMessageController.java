package com.uww.messaging.controller.team;

import com.google.gson.Gson;
import com.uww.messaging.contract.MessageService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.Response;
import com.uww.messaging.display.TeamMessageDisplay;
import com.uww.messaging.display.TeamMessageRequestBody;
import com.uww.messaging.model.TeamMessage;
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

/**
 * Created on 3/22/16
 *
 * @author reinaldo
 */
@Controller
@RequestMapping(value = "user/team/message")
public class TeamMessageController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/list/all/message", method = RequestMethod.GET)
	@ResponseBody
	public String listTeamMessages(Authentication authentication, @RequestParam("teamId") int teamId) {

		List<TeamMessageDisplay> messagesFromTeam = messageService.findMessagesFromTeam(teamId);

		return new Gson().toJson(messagesFromTeam);
	}

	@RequestMapping(value = "/list/new/message",method = RequestMethod.GET)
	@ResponseBody
	public String listNewTeamMessages(Authentication authentication, @RequestParam("teamId") int teamId){
		User user = userService.userByAuthentication(authentication);
		List<TeamMessage> newMessages = messageService.findNewMessagesFromTeam(user,teamId);

		return new Gson().toJson(newMessages);
 	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Response insertTeamMessage(Authentication authentication, @RequestBody TeamMessageRequestBody param) {

		int userId = userService.userByAuthentication(authentication).getUserId();

		messageService.sendMessageToTeam(userId, param.getToTeamId(), param.getMessage());

		return new Response(true, "message sent from user id " + userId + " to team id " + param.getToTeamId(), " ");

	}
}
