package com.uww.messaging.controller;

import com.google.gson.Gson;
import com.uww.messaging.contract.MessageService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.model.TeamMessage;
import com.uww.messaging.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/team/message")
public class TeamMessageController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/list/all/message", method = RequestMethod.GET)
	@ResponseBody
	public String listTeamMessages(Authentication authentication, @RequestParam("teamId") int teamId) {

		Gson gson = new Gson();

		List<TeamMessage> messagesFromTeam = messageService.findMessagesFromTeam(teamId);
		return gson.toJson(messagesFromTeam);
	}

	@RequestMapping(value = "/list/new/message",method = RequestMethod.GET)
	@ResponseBody
	public String listNewTeamMessages(Authentication authentication, @RequestParam("teamId") int teamId){
		User user = userService.userByAuthentication(authentication);
		List<TeamMessage> newMessages = messageService.findNewMessagesFromTeam(user,teamId);

		return new Gson().toJson(newMessages);
 	}

	@RequestMapping(value = "/insert", method = RequestMethod.PUT)
	@ResponseBody
	public String insertTeamMessage(Authentication authentication, @RequestParam("toTeamId") int toTeamId, @RequestParam("message") String message) {

		int userId = userService.userByAuthentication(authentication).getUserId();

		messageService.sendMessageToTeam(userId, toTeamId, message);

		return "redirect:/user";
	}
}
