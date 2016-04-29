package com.uww.messaging.controller.user;

import com.google.gson.Gson;
import com.uww.messaging.contract.TeamService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by horvste on 1/19/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "")
    public String index(Authentication authentication, Model model) {
        User currentUser = userService.getLoggedInUser(authentication);

        currentUser.setLastLoggedIn(new Timestamp(Calendar.getInstance().getTimeInMillis()));

        userService.save(currentUser);

        model.addAttribute("user", currentUser);
        model.addAttribute("teams", teamService.findTeamsByUserId(currentUser.getUserId()));
        return "User/index";
    }

    @RequestMapping(value = "/valid", method = RequestMethod.GET)
    public ResponseEntity valid(@RequestParam("username") String username) {
        if (userService.exists(username)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

	@RequestMapping(value = "/get/current/user")
	@ResponseBody
	public String getCurrentUser(Authentication authentication){
		User currentUser = userService.getLoggedInUser(authentication);

		return new Gson().toJson(currentUser);
	}
}
