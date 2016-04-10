package com.uww.messaging.controller.user;

import com.uww.messaging.contract.TeamService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.Calendar;


/**
 * Created on 4/9/16
 *
 * @author reinaldo
 */
@Controller
@RequestMapping(value = "/user/content" )
public class UserContentController {

	@Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;


	@RequestMapping(value = "/wiki")
	public String wiki(Authentication authentication, Model model){
		User currentUser = userService.getLoggedInUser(authentication);

		currentUser.setLastLoggedIn(new Timestamp(Calendar.getInstance().getTimeInMillis()));

		userService.save(currentUser);

		model.addAttribute("user", currentUser);
		model.addAttribute("teams", teamService.findTeamsByUserId(currentUser.getUserId()));
		return "User/wiki";
	}
}
